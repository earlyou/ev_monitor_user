var stompClient = null;
var id = null;
// 서버소켓에 연결
function connect() {
	var socket = new SockJS('/ws');
	stompClient = Stomp.over(socket);

	stompClient.connect({}, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		stompClient.subscribe('/sends/' + id, function(msg) {
			$("#chatmsg").append(
					'<div class="chat_message_wrapper chat_message_right">'+
					'	<ul class="chat_message">'+
					'		<li>'+
					'			<p>'+JSON.parse(msg.body).content1+'</p>'+
					'		</li>'+
					'	</ul>'+
					'</div>');
			$("#chatmsg").append(
					'<div class="chat_message_wrapper">'+
					'	<div class="chat_user_avatar">'+
					'		<img src="/images/chatbotlogo.png" class="md-user-image">'+
					'	</div>'+
					'	<ul class="chat_message">'+
					'		<li>'+
					'			<p>'+JSON.parse(msg.body).content2+'</p>'+
					'		</li>'+
					'	</ul>'+
					'</div>');
		});
		stompClient.subscribe('/send/serversend', function(msg) {
			$("#servermsg1").text(JSON.parse(msg.body).content1);
			$("#servermsg2").text(JSON.parse(msg.body).content2);
		});
	});
}

// connect&disconnect버튼 활성화/비활성화
function setConnected(connected) {
	if (connected) {
		$("#status").text("Connected");
	} else {
		$("#status").text("Disconnected");
	}

}
// 서버소켓에 연결끊기
function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function clearTextarea() { // 텍스트 입력 후 전송 시 input 초기화
	$('#metext').val('');
}

function scrollDown() { // 텍스트 입력 후 전송 시 스크롤 아래로
	$('#chat').scrollTop($('#chat').prop('scrollHeight'));
}

function sendMe() {

	var msg = JSON.stringify({
		'sendid' : id,
		'content1' : $('#metext').val()
	});
	stompClient.send("/receiveme", {}, msg);
	clearTextarea();

}

$(document).ready(function() {
	$("#connect").click(function() {
		connect();
	});
	$("#disconnect").click(function() {
		disconnect();
	});
	$("#sendme").click(function() {
		sendMe();
		scrollDown();
	});
});

$(function() {
	$("#connect").click(function() {
		$('#sidebar_secondary').addClass('popup-box-on');
	});

	$("#disconnect").click(function() {
		$('#sidebar_secondary').removeClass('popup-box-on');
	});
})