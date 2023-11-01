let stompClient = null;
let socket = new SockJs("/ws");

stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
  console.log("Connected!!");

  stompClient.subscribe("/log", function (data) {
    print(JSON.parse(data.body));
  });

  function print(message) {
    let log = document.getElementById("log-watcher");
    let para = document.createElement("p");
    let message = document.createTextNode(message.content);
    para.appendChild(message);
    log.appendChild(para);
  }
});
