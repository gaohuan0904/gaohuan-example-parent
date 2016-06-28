<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
welcome<br/>
<button onclick="send()">Send</button>
<div id="message"></div>
</body>
<script src="static/js/sockjs.js"></script>
<script src="static/js/stomp.js"></script>
<script type="text/javascript">
    var socket = new SockJS('/coordination');
    var stompClient = Stomp.over(socket);

    stompClient.connect({}, function () {
        stompClient.subscribe('/topic/notice', function (chat) {
            document.getElementById("message").innerHTML += JSON.stringify(chat.body) + "<br/>";
        });
    });

    function send() {
        stompClient.send("/app/userChat", {}, JSON.stringify({
            'name': encodeURIComponent("test"),
            'chatContent': encodeURIComponent("chatTest"),
            'coordinationId': '000000'
        }));
    }


</script>
</html>