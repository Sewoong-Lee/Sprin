const eventSource = new EventSource(
  "http://localhost:8082/sender/qq/receiver/ww"
);

eventSource.onmessage = (e) => {
  //console.log(1, e);
  const data = JSON.parse(e.data);
  console.log(2, data);
  initMessage(data);
};

function getSengMsgBox(sendmsg, time) {
  return `<div class="sent_msg">
    <p>${sendmsg} </p>
    <span class="time_date"> 
    ${time}</span>
  </div>`;
}

function initMessage(data) {
  let chatbox = document.querySelector("#chat-box");

  //인풋박스 값 가져오기
  let msginput = document.querySelector("#chat-outgoing-msg");

  let chatOutbox = document.createElement("div");
  chatOutbox.className = "outgoing_msg";

  let md = data.createdAt.substring(5, 10);
  let tm = data.createdAt.substring(11, 16);
  convertTime = tm + " | " + md;

  //가져온 값 함수에 던져주기
  chatOutbox.innerHTML = getSengMsgBox(data.msg, convertTime);
  chatbox.append(chatOutbox);
  //어펜드 후 값 비움
  msginput.value = "";
}

async function addMessage() {
  let chatbox = document.querySelector("#chat-box");

  //인풋박스 값 가져오기
  let msginput = document.querySelector("#chat-outgoing-msg");
  let sendmsg = msginput.value;

  let chatOutbox = document.createElement("div");
  chatOutbox.className = "outgoing_msg";

  let date = new Date();
  let now =
    date.getHours() +
    ":" +
    date.getMinutes() +
    "|" +
    date.getMonth() +
    "/" +
    date.getDate();

  //데이터서버에 전송
  let chat = {
    sender: "qq",
    receiver: "ww",
    msg: sendmsg,
  };

  let respones = await fetch("http://localhost:8082/chat", {
    method: "post",
    body: JSON.stringify(chat), //js를 json으로 변경
    headers: {
      //보내는 데이터 타입 (마임타입)
      "Content-Type": "application/json;charset=UTF-8",
    },
  });

  console.log(respones);

  //데이터 파싱
  let parseRespones = await respones.json();

  console.log(parseRespones);

  //가져온 값 함수에 던져주기
  //   chatOutbox.innerHTML = getSengMsgBox(sendmsg, now);
  //   chatbox.append(chatOutbox);
  //어펜드 후 값 비움
  msginput.value = "";
}

document.querySelector("#chat-send").addEventListener("click", () => {
  addMessage();
});

document
  .querySelector("#chat-outgoing-msg")
  .addEventListener("keydown", (e) => {
    console.log(e.keyCode); //키 코드 확인
    if (e.keyCode === 13) {
      addMessage();
    }
  });
