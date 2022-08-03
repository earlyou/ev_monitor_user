function getstation(station) {
	var start = new Date();
	var carmodelid = $('select[name="carmodelid"]').val();
	
	var adapterArray = new Array();
	$('input:checkbox[name="adapter"]:checked').each(function() {
		adapterArray.push(this.value);
	});
 	var adapter = adapterArray.join(',');
 	
 	// 전체버튼 설정
 	var bnmArray = new Array();
 	if($('#all').is(":checked")){
 		var bnm = "";
 	}else{
		$('input:checkbox[name="bnm"]:checked').each(function() {
			bnmArray.push(this.value);
		});
		var bnm = bnmArray.join(',');
 	}
	
 	var chargespeedArray = new Array();
	$('input:checkbox[name="chargespeed"]:checked').each(function() {
		chargespeedArray.push(this.value);
	});
 	var chargespeed = chargespeedArray.join(',');
 	
 	var chargerstatArray = new Array();
	$('input:checkbox[name="chargerstat"]:checked').each(function() {
		chargerstatArray.push(this.value);
	});
 	var chargerstat = chargerstatArray.join(',');
 	
 	var parkingFreeArray = new Array();
	$('input:checkbox[name="parkingFree"]:checked').each(function() {
		parkingFreeArray.push(this.value);
	});
 	var parkingFree = parkingFreeArray.join(',');
 	
	var filter = "CA";
 	
 	// filter 설정 및 빈값에 null 혹은 0 설정
 	
 	// adapter
 	if((adapter.trim() === "") || (adapter === undefined) || (adapter === null)){
 		adapter = null;
 	}
 	// bnm
 	if(!(bnm.trim() === "") && !(bnm === undefined) && !(bnm === null)){
 		filter += "B";
 	}else{
 		bnm = null;
 	}
 	// chargespeed
 	if(!(chargespeed.trim() === "") && !(chargespeed === undefined) && !(chargespeed === null)){
 		filter += "S";
 	}else{
 		chargespeed = null;
 	}
 	// chargerstat
 	if(!(chargerstat.trim() === "") && !(chargerstat === undefined) && !(chargerstat === null)){
 		filter += "T";
 	}else{
 		chargerstat = null;
 	}
 	// parkingFree
 	if(!(parkingFree.trim() === "") && !(parkingFree === undefined) && !(parkingFree === null)){
 		filter += "P";
 	}else{
 		parkingFree = null;
 	}
	$.ajax({
		url:'/evcsmonitor/getstation',
		data:{'filter':filter,'carmodelid':carmodelid,'adapter':adapter,'bnm':bnm,'chargespeed':chargespeed,'chargerstat':chargerstat,'parkingFree':parkingFree},
		async: false,
		success:function(data){
			station = JSON.parse(data);
		}
	});
	var end = new Date();
	console.log('getstation: '+(end-start)/1000+'초');
	return station;
}


function mkmarker(station,chger,map,markers) {
	var start = new Date();
	$.each(station,function(i,v){
	    
		var content = 
		'<div class="wrap">' + 
		'    <div class="info">' + 
		'        <div class="title">' + 
		'           <div class="Nm">' + v.statNm + 
		'			</div>' +
		'           <div class="bookmark">' +
		'           	<a href="#" id="'+v.statId+'b'+'" class="icon"><img id="'+v.statId+'" src="images/bookmark/unchecked.png" width="20" height="20"></a>' +
		'			</div>' + 
		'			<div class="close" title="닫기"></div>' + 
		'        </div>' + 
		'        <div class="body">' + 
		'           <div class="img">' +
		'           	<img src="https://i1.daumcdn.net/dmaps/apis/n_local_blit_04.png" width="73" height="70">' +
		'			</div>' + 
		'			<div class="desc">' + 
		'			<div>'+
		'				<p>충전소 명: '+v.statNm+
		'				</p><p>충전소ID: '+v.statId+
		'				</p><p>주소: '+v.addr+
		'				</p><p>상세위치: '+v.location+
		'				</p><p>위도: '+v.lat+
		'				</p><p>경도: '+v.lng+
		'				</p><p>이용가능한시간: '+v.useTime+
		'				</p><p>기관 아이디: '+v.busiId+
		'				</p><p>기관명: '+v.busiNm+
		'				</p><p>운영기관명: '+v.busiNm+
		'				</p><p>운영기관연락처: '+v.busiCall+
		'				</p><p>지역코드: '+v.zcode+
		'				</p><p>주차료무료: '+v.parkingFree+
		'				</p><p>충전소 안내: '+v.note+
		'				</p><p>이용자 제한: '+v.limitYn+
		'				</p><p>이용제한 사유: '+v.limitDetail+
		'			</div>'+
		'			<div class="chger">';

		var output = [];
	    $.each(chger, function(i,c){
        	if (c.statId == v.statId) {
        		output.push(c.output);
        		
	        	var status = '';
	        	if (c.stat == 1) {
					status = '통신이상';
				}else if (c.stat == 2) {
					status = '충전대기';
				}else if (c.stat == 3) {
					status = '충전 중';
				}else if (c.stat == 4) {
					status = '운영중지'
				}else if (c.stat == 5) {
					status = '점검 중'
				}else if (c.stat == 9) {
					status = '상태미확인'
				}
        		content = content +
		'				<p>'+
		'					<span>'+c.chgerId+'번 충전기'+' : '+status+'</span>'+
		'				</p>'+
		'				<p>'+
		'					<span>충전 용량'+' : '+c.output+'</span>'+
		'				</p>';
			}
        });
	    
	    content = content +
        '            </div>' + 
        '        </div>' + 
        '    </div>' +    
        '</div>';

        var locations = new Object();
		locations.title = v.statNm;
		locations.latlng = new kakao.maps.LatLng(v.lat, v.lng);
		
		// 마커 이미지의 이미지 주소
		var imageSrc = 'images/markers/1.png';
		
		if (Math.max.apply(Math,output) < 40) {
			imageSrc = 'images/markers/1.png';
		}else if(Math.max.apply(Math,output) >= 100) {
			imageSrc = 'images/markers/3.png';
		}else if(Math.max.apply(Math,output) >= 40) {
			imageSrc = 'images/markers/2.png';
		}
		
		// 마커 이미지 크기
	    var imageSize = new kakao.maps.Size(24, 36);
	    // 마커 이미지 생성   
	    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
	    // 마커 생성
		var marker = new kakao.maps.Marker({
		    map: map, // 마커를 표시할 지도
		    position: locations.latlng, // 마커를 표시할 위치가 담긴 배열
		    title : locations.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시
		    image : markerImage, // 마커 이미지
		    clickable: true,	// 클릭 가능 여부
		    zIndex: 1		// 컴포넌트의 우선순위, zIndex가 클 수록 앞으로 표시
		});
        
     	// 마커에 Custom Overlay 붙이기
	    var overlay = new kakao.maps.CustomOverlay({
			content : content,
			map : map,
			clickable: true,
			position : marker.getPosition(),
			zIndex: 4
		});
     	
		$.each(bookmark,function(i,b){
			if(b.statid == v.statId) {
				$('#'+v.statId+'').attr('src', 'images/bookmark/checked.png');
			}
		});
		
		$('#'+v.statId+'b').on('click',function(){
			if (session.loginmember.id == null) {
				if(confirm('로그인 하시겠습니까?')){
					$(location).attr('href','/evcsmonitor/login')
				}
			}else {
				if ($('#'+v.statId+'').attr("src") == 'images/bookmark/unchecked.png') {
					$('#'+v.statId+'').attr('src', 'images/bookmark/checked.png');
					
					$.ajax({
						url:'/evcsmonitor/addbookmark',
						type: "POST",
						data: {'statId':v.statId,'uid':session.loginmember.id}
					});
				
				}else if ($('#'+v.statId+'').attr("src") == 'images/bookmark/checked.png') {
					$('#'+v.statId+'').attr('src', 'images/bookmark/unchecked.png');
					
					$.ajax({
						url:'/evcsmonitor/rmbookmark',
						type: "POST",
						data: {'statId':v.statId,'uid':session.loginmember.id}
					});
				}
			}
		});
		
		

        $('.wrap').mouseenter(function(){
        	map.setDraggable(false);
        	map.setZoomable(false);
        });
        $('.wrap').mouseleave(function(){
        	map.setDraggable(true);
        	map.setZoomable(true);
        });
	    
	 	// 마커에 커스텀 오버레이 클릭 이벤트(열기,닫기) 붙이기
		kakao.maps.event.addListener(marker, 'click', function() {
		    overlay.setMap(map);
		    $('.close').click(function(){
	        	map.setDraggable(true);
	        	map.setZoomable(true);
		    	overlay.setMap(null);
		    });
		});
		overlay.setMap(null);			// 마커 누르기 전에는 오버레이 표시 금지
     	
	    
	    marker.setMap(map);
	    markers.push(marker);
	    marker.setVisible(false);
	});
	if (map.getLevel() <= 5) {
		var bounds = map.getBounds();
		$.each(markers, function(i,v){
			if(v.getPosition().getLat() >= bounds.qa && v.getPosition().getLat() <= bounds.pa && 
					v.getPosition().getLng() >= bounds.ha && v.getPosition().getLng() <= bounds.oa){
				v.setVisible(true);
			}else {
				if(v.getVisible() == true){
					v.setVisible(false);
				}
			}
		});
	}
	var end = new Date();
	console.log('mkmarker: '+(end-start)/1000+'초');
}

function getchger(chger){
	var start = new Date();
	$.ajax({
		url:'/evcsmonitor/getchger',
		data:{},
		async:false,
		success:function(data){
			chger = JSON.parse(data);
		}
	});
	var end = new Date();
	console.log('getchger: '+(end-start)/1000+'초');
	return chger;
}

function ready(lat,lng){
	var start = new Date();
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 선언
    mapOption = { 
        center: new kakao.maps.LatLng(lat, lng), // 지도 초기 중심좌표 설정
        level: 10 // 지도의 확대 레벨, 숫자가 높을 수록 고도가 높아짐
    };
	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도 생성
	// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
	var zoomControl = new kakao.maps.ZoomControl();
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
	
	var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        gridSize: 80,
        minLevel: 6, // 클러스터 할 최소 지도 레벨
        disableClickZoom: false, // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
        zIndex: 2
    });
	clusterer.setMinClusterSize(1);
	var markers = [];
	
	var chger = getchger(chger);
	var station = getstation(station);
	mkmarker(station,chger,map,markers);
	clusterer.addMarkers(markers);

	var end = new Date();
	console.log('document ready: '+(end-start)/1000+'초');
	
	$('#adapter').hide();

	$('select[name="carmodelid"]').change(function(){
		$('input:checkbox[name="adapter"]:checked').prop('checked',false);
		var id = $('select[name="carmodelid"]').val();
		if(id==62||id==701){
			$('#adapter').show();
			if(id==62){
				$('#2').hide();
				$('#3').hide();
				$('#4').hide();
			}else{
				$('#2').show();
				$('#3').show();
				$('#4').show();
			}
		}else{
			$('#adapter').hide();
		};
	});
	
	$('#resetfilter').click(function() {
		$('#all').prop('checked',false);
		$('input:checkbox[name="adapter"]:checked').prop('checked',false);
		$('input:checkbox[name="bnm"]:checked').prop('checked',false);
		$('input:checkbox[name="chargespeed"]:checked').prop('checked',false);
		$('input:checkbox[name="chargerstat"]:checked').prop('checked',false);
		$('input:checkbox[name="parkingFree"]:checked').prop('checked',false);
	});
	
	$('#all').change(function(){
		if($('#all').is(":checked")){
			$('input:checkbox[name="bnm"]').prop('checked',true);
		}else{
			$('input:checkbox[name="bnm"]').prop('checked',false);
		}
	});
	$('input:checkbox[name="bnm"]').change(function(){
		if($('input:checkbox[name="bnm"]:checked').length == $('input:checkbox[name="bnm"]').length){
			$('#all').prop('checked',true);
		}else{
			$('#all').prop('checked',false);
		}
	});
	
	$('#sel3').change(function(){
		// 지도 중앙에 위치하게 할 지역
		var coord = $('select[name="gu"]').val();
		var latlng = coord.split(',');
		var lat = latlng[0];
		var lng = latlng[1];
		var moveLatLng = new kakao.maps.LatLng(lat,lng);
		map.panTo(moveLatLng);
	});
	
	$('#addfilter').click(function(){
		var start = new Date();
		clusterer.removeMarkers(markers);
		$.each(markers,function(i,v){
			v.setMap(null);
		});
		markers = [];
		chger = getchger(chger);
		station = getstation(station);
		mkmarker(station,chger,map,markers);
		clusterer.addMarkers(markers);
		
		var end = new Date();
		console.log('filter changed: '+(end-start)/1000+'초');
	});
	
	kakao.maps.event.addListener(map, 'idle', function(){
		if (map.getLevel() <= 5) {
			var bounds = map.getBounds();
			$.each(markers, function(i,v){
				if(v.getPosition().getLat() >= bounds.qa && v.getPosition().getLat() <= bounds.pa && 
						v.getPosition().getLng() >= bounds.ha && v.getPosition().getLng() <= bounds.oa){
					v.setVisible(true);
				}
			});
		}
	});
}

$(document).ready(function(){
	if (navigator.geolocation) { 
        navigator.geolocation.getCurrentPosition(function(position){
			var lat = position.coords.latitude;
			var lng = position.coords.longitude;
			ready(lat,lng);
		});
    } else {
		var lat = 37.55702316206885;
		var lng = 126.99452229408297;
        alert('사용자의 위치 정보를 받아올 수 없습니다.');
        ready(lat,lng);
    }
});