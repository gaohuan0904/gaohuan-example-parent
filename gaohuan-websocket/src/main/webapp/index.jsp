<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
welcome<br/>
<input id="text" type="text">
<button onclick="send()">Send</button>
<button onclick="closeWebSocket()">Close</button>
<div id="message"></div>
</body>
<script type="text/javascript">

    var webSocket = null;
    if ('WebSocket' in window) {
        webSocket = new WebSocket("ws://localhost:8080/webSocket");
    } else {
        alert("Not support webSocket");
    }

    webSocket.onerror = function () {
        setMessage("error");
    }

    webSocket.onopen = function () {
        setMessage("open");
    }

    webSocket.onmessage = function () {
        setMessage(event.data);

    }

    webSocket.onclose = function () {
        setMessage("close");
    }

    function setMessage(innerHtml) {
        document.getElementById("message").innerHTML += innerHtml + "<br/>";
    }

    function send() {
        var message = document.getElementById("text").value;
        webSocket.send(message);
    }

    function closeWebSocket() {
        webSocket.close();
    }

</script>
</html>