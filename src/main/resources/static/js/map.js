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

function mkmarker(station,chger,map,markers,stationId) {
	var start = new Date();
	$.each(station,function(i,v){
		if (v.delYn != 'N') {
		    var parkingfree = '현장 확인'
			if (v.parkingFree == 'Y') {
				parkingfree = '무료';
			}else if (v.parkingFree == 'N') {
				parkingfree = '유료';
			}else if (v.parkingFree == '') {
				parkingfree = '현장 확인';
			}
			
			var limitdetail = '해당사항 없음';
			if (v.limitYn == 'Y') {
				limitdetail = v.limitDetail;
			} else {
				limitdetail = '해당사항 없음';
			}
			
			var loc = '정보 없음';
			if (v.location != 'null') {
				loc = v.location;
			}else {
				loc = '정보 없음';
			}
			
			var busicall = '';
			if (v.busiCall != 'null') {
				busicall = v.busiCall;
			}else {
				busicall = '';
			}
		    
			var content = 
			'<div class="wrap" style="white-space: normal;">' + 
			'    <div class="info">' + 
			'        <div class="title">' + 
			'           <div class="Nm">' + v.statNm + '</div>';
			
					content = content + 
			'           <div class="modalbutton" style="margin-top: 7px;">' +
			'           	<a href="#" class="icon alarm" onfocus="this.blur()" style="text-decoration:none;"><img src="images/alarm.png" width="20" height="20"></a>' +
			'			</div>';
			
			var boo = false;
			$.each(bookmark,function(i,b){
				if(b.statid == v.statId) {
					boo = true;
					return boo;
				}
			});
			
			if(boo) {
					content = content + 
			'           <div class="bookmark" style="margin-top: 7px;">' +
			'           	<a href="#" id="'+v.statId+'" class="icon bmark"><img src="images/bookmark/checked.png" width="20" height="20"></a>' +
			'			</div>';
			}else {
					content = content + 
			'           <div class="bookmark" style="margin-top: 7px;">' +
			'           	<a href="#" id="'+v.statId+'" class="icon bmark"><img src="images/bookmark/unchecked.png" width="20" height="20"></a>' +
			'			</div>';
				}
			
			content = content +
			'			<div class="close" title="닫기"></div>' + 
			'		</div>' + 
			'		<div class="body">' + 
			'			<div class="desc">' + 
			'				<div class="table-wrapper" style="white-space: normal;">'+
			'					<table class="table table-hover" style="margin-bottom:10px;margin-top:10px">'+
			'						<tr>'+
			'							<th>주소</th>'+
	        '							<td>'+v.addr+'</td>'+
			'						</tr>'+
			'						<tr>'+
			'							<th>상세 위치</th>'+
	        '							<td>'+loc+'</td>'+
			'						</tr>'+
			'						<tr>'+
			'							<th>이용 가능 시간</th>'+
	        '							<td>'+v.useTime+'</td>'+
			'						</tr>'+
			'						<tr>'+
			'							<th>운영 기관</th>'+
	        '							<td>'+v.bnm+'</td>'+
	        '						</tr>'+
			'						<tr>'+
			'							<th>운영 기관 연락처</th>'+
	        '							<td>'+busicall+'</td>'+
			'						</tr>' + 
			'						<tr>'+
			'							<th>주차비</th>'+
	    	'							<td>'+parkingfree+'</td>'+
			'						</tr>' + 
			'						<tr>'+
			'							<th>이용자 제한</th>'+
	        '							<td>'+limitdetail+'</td>'+
			'						</tr>' + 
			'   	     		</table>' + 
			'				</div>'+
			'				<div class="table-wrapper">'+
			'					<table class="table table-hover" style="margin-bottom:10px;margin-top:10px">'+
			'						<thead style="text-align:center">'+
			'							<tr>'+
			'								<th>충전용량</th><th>충전기 타입</th><th>상태</th>'+
			'							</tr>'+
			'						<thead>'+
			'						<tbody>';
	
			var output = [];
		    $.each(chger, function(i,c){
	        	if (c.statId == v.statId) {
	        		output.push(c.output);
	        		
		        	var status = '';
		        	var outclass = '';
		        	var type = '';
		        	if (c.stat == 1) {
						status = '통신이상';
					}else if (c.stat == 2) {
						status = '충전 가능';
					}else if (c.stat == 3) {
						status = '충전 중';
					}else if (c.stat == 4) {
						status = '운영 중지'
					}else if (c.stat == 5) {
						status = '점검중'
					}else if (c.stat == 9) {
						status = '상태미확인'
					}
					if (c.output < 40) {
						c.output = c.output + 'kW'
						outclass = '(완속)';
					}else if (c.output >= 100) {
						c.output = c.output + 'kW'
						outclass = '(초급속)';
					}else if (c.output >= 40) {
						c.output = c.output + 'kW'
						outclass = '(급속)';
					}else if (c.output == null) {
						c.output = '정보 없음';
						outclass = '';
					}
					
					var DCcha = 
					'<div class="dropdown dropend">'+
					'	<a class="icon" href="#" data-bs-toggle="dropdown">'+
					'		<img src="images/chgerimg/DC_CHAdeMO.png" width="40px">'+
					'	</a>'+
					'	<ul class="dropdown-menu" style="overflow:scroll;height:140px;">'+
					'		<li><span class="dropdown-item-text">DC차데모</span></li>'+
					'		<li><hr class="dropdown-divider"></li>'+
					'		<li><span class="dropdown-item-text">블루온</span></li>'+
					'		<li><span class="dropdown-item-text">레이</span></li>'+
					'		<li><span class="dropdown-item-text">쏘울</span></li>'+
					'		<li><span class="dropdown-item-text">아이오닉</span></li>'+
					'		<li><span class="dropdown-item-text">Leaf</span></li>'+
					'	</ul>'+
					'</div>';
						
					var AC = 
					'<div class="dropdown dropend">'+
					'	<a class="icon" href="#" data-bs-toggle="dropdown">'+
					'		<img src="images/chgerimg/AC.png" width="40px">'+
					'	</a>'+
					'	<ul class="dropdown-menu" style="overflow:scroll;height:140px;">'+
					'		<li><span class="dropdown-item-text">AC완속</span></li>'+
					'		<li><hr class="dropdown-divider"></li>'+
					'		<li><span class="dropdown-item-text">블루온</span></li>'+
					'		<li><span class="dropdown-item-text">레이</span></li>'+
					'		<li><span class="dropdown-item-text">쏘울</span></li>'+
					'		<li><span class="dropdown-item-text">아이오닉</span></li>'+
					'		<li><span class="dropdown-item-text">스파크</span></li>'+
					'		<li><span class="dropdown-item-text">i3</span></li>'+
					'		<li><span class="dropdown-item-text">Leaf</span></li>'+
					'		<li><span class="dropdown-item-text">볼트</span></li>'+
					'	</ul>'+
					'</div>';
					
					var AC3 = 
					'<div class="dropdown dropend">'+
					'	<a class="icon" href="#" data-bs-toggle="dropdown">'+
					'		<img src="images/chgerimg/AC3.png" width="40px">'+
					'	</a>'+
					'	<ul class="dropdown-menu" style="overflow:scroll;height:140px;">'+
					'		<li><span class="dropdown-item-text">AC3상</span></li>'+
					'		<li><hr class="dropdown-divider"></li>'+
					'		<li><span class="dropdown-item-text">SM3 ZE</span></li>'+
					'	</ul>'+
					'</div>';
					
					var DCcombo = 
					'<div class="dropdown dropend">'+
					'	<a class="icon" href="#" data-bs-toggle="dropdown">'+
					'		<img src="images/chgerimg/DC_combo.png" width="40px">'+
					'	</a>'+
					'	<ul class="dropdown-menu" style="overflow:scroll;height:140px;">'+
					'		<li><span class="dropdown-item-text">DC콤보</span></li>'+
					'		<li><hr class="dropdown-divider"></li>'+
					'		<li><span class="dropdown-item-text">아이오닉</span></li>'+
					'		<li><span class="dropdown-item-text">스파크</span></li>'+
					'		<li><span class="dropdown-item-text">볼트</span></li>'+
					'		<li><span class="dropdown-item-text">i3</span></li>'+
					'		<li><span class="dropdown-item-text">코나</span></li>'+
					'		<li><span class="dropdown-item-text">쏘울</span></li>'+
					'		<li><span class="dropdown-item-text">니로</span></li>'+
					'	</ul>'+
					'</div>';
					
					if (c.chgerType == 1) {
						// DC차데모
						type = DCcha;
					}else if (c.chgerType == 2) {
						// AC완속
						type = AC;
					}else if (c.chgerType == 3) {
						// DC차데모+AC3상
						type = DCcha+AC3;
					}else if (c.chgerType == 4) {
						// DC콤보
						type = DCcombo;
					}else if (c.chgerType == 5) {
						// DC차데모+DC콤보
						type = DCcha + DCcombo;
					}else if (c.chgerType == 6) {
						// DC차데모+AC3상+DC콤보
						type = DCcha + AC3 + DCcombo;
					}else if (c.chgerType == 7) {
						// AC3상
						type = AC3;
					}
	        		content = content + 
	        		'					<tr>'+
	        		'						<td>'+c.output+outclass+'</td>'+
	        		'						<td>'+type+'</td>'+
	        		'						<td>'+status+'</td>'+
	        		'					</tr>';
				}
	        });
		    
		    content = content +
			'	            		</tbody>' + 
			'   	     		</table>' + 
	    	'    			</div>' +    
	        '			</div>'+
	        '		</div>'+
	        '	</div>'+
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
				xAnchor: 0.5,
				zIndex: 4
			});
	
		 	// 마커에 커스텀 오버레이 클릭 이벤트(열기,닫기) 붙이기
			kakao.maps.event.addListener(marker, 'click', function() {
			    overlay.setMap(map);
			    $('.close').click(function(){
		        	map.setDraggable(true);
		        	map.setZoomable(true);
			    	overlay.setMap(null);
			    });
			    
			    $('.wrap').on('mouseenter',function(){
		        	map.setDraggable(false);
		        	map.setZoomable(false);
		        });
		        $('.wrap').on('mouseleave',function(){
		        	map.setDraggable(true);
		        	map.setZoomable(true);
		        });
		        $('.alarm').on('click',function(e){
		        	e.preventDefault();
		        	if (session.loginmember == null) {
						if(confirm('로그인 하시겠습니까?')){
							$(location).attr('href','/evcsmonitor/login')
						}
					}else if(session.loginmember != null){
						// form에 v.statid, uid val 입력
						$('#statid').val(v.statId);
						
						$("#myModal").modal('show');
					}
			    });
			    
			    $('.bmark').on('click',function(e){
					e.preventDefault();		// 북마크 버튼 누를 때 스크롤이 움직이는 현상 방지
					if (session.loginmember == null) {
						if(confirm('로그인 하시겠습니까?')){
							$(location).attr('href','/evcsmonitor/login')
						}
					}else if(session.loginmember != null){
						if ($('#'+v.statId+' img').attr("src") == 'images/bookmark/unchecked.png') {
							$('#'+v.statId+' img').attr('src', 'images/bookmark/checked.png');
							
							$.ajax({
								url:'/evcsmonitor/addbookmark',
								data: {'statId':v.statId,'uid':session.loginmember.id},
								success:function(){
									console.log('bookmark added');
									$('#asdf').append('<li id="'+v.statId+v.statId+'"><a style="font-size: 15px;" onclick="javascript:movemap("'+v.statId+'")">'+v.statNm+'</a></li>');
									$("#asdf").animate({ scrollTop: $('#asdf').prop("scrollHeight")}, 1000);
								}
							});
						
						}else if ($('#'+v.statId+' img').attr("src") == 'images/bookmark/checked.png') {
							$('#'+v.statId+' img').attr('src', 'images/bookmark/unchecked.png');
							
							$.ajax({
								url:'/evcsmonitor/rmbookmark',
								data: {'statId':v.statId,'uid':session.loginmember.id},
								success:function(){
									console.log('bookmark removed');
									$('#'+v.statId+v.statId).remove();
								}
							});
						}
					}
				});
			});
			
			overlay.setMap(null);			// 마커 누르기 전에는 오버레이 표시 금지
			
			// 즐겨찾기 충전소 찾기를 눌렀을 때 작동
	     	if(stationId == v.statId) {
				overlay.setMap(map);
				$('.close').click(function(){
		        	map.setDraggable(true);
		        	map.setZoomable(true);
			    	overlay.setMap(null);
			    });
			    
			    $('.wrap').on('mouseenter',function(){
		        	map.setDraggable(false);
		        	map.setZoomable(false);
		        });
		        $('.wrap').on('mouseleave',function(){
		        	map.setDraggable(true);
		        	map.setZoomable(true);
		        });
			    
			    $('.bmark').on('click',function(e){
					e.preventDefault();		// 북마크 버튼 누를 때 스크롤이 움직이는 현상 방지
					if (session.loginmember == null) {
						if(confirm('로그인 하시겠습니까?')){
							$(location).attr('href','/evcsmonitor/login')
						}
					}else if(session.loginmember != null){
						if ($('#'+v.statId+' img').attr("src") == 'images/bookmark/unchecked.png') {
							$('#'+v.statId+' img').attr('src', 'images/bookmark/checked.png');
							
							$.ajax({
								url:'/evcsmonitor/addbookmark',
								data: {'statId':v.statId,'uid':session.loginmember.id},
								success:function(){
									console.log('bookmark added');
									$('#asdf').append('<li id="'+v.statId+v.statId+'"><a style="font-size: 15px;" onclick="javascript:movemap("'+v.statId+'")">'+v.statNm+'</a></li>');
									$("#asdf").animate({ scrollTop: $('#asdf').prop("scrollHeight")}, 1000);
								}
							});
						
						}else if ($('#'+v.statId+' img').attr("src") == 'images/bookmark/checked.png') {
							$('#'+v.statId+' img').attr('src', 'images/bookmark/unchecked.png');
							
							$.ajax({
								url:'/evcsmonitor/rmbookmark',
								data: {'statId':v.statId,'uid':session.loginmember.id},
								success:function(){
									console.log('bookmark removed');
									$('#'+v.statId+v.statId).remove();
								}
							});
						}
					}
				});
			}
			
		    marker.setMap(map);
		    markers.push(marker);
		    marker.setVisible(false);
	    }
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

function ready(lat,lng,stationId,map){
	var start = new Date();
	
	// 이동할 위도 경도 위치를 생성합니다 
    var moveLatLon = new kakao.maps.LatLng(lat, lng);
    
    // 지도 중심을 이동 시킵니다
    map.setCenter(moveLatLon);
	
	// 즐겨찾는 충전소를 찾을 때는 지도가 더 확대가 된 상태로 로드
    if (stationId != '') {
		map.setLevel(4);
	}
	
	// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
	var zoomControl = new kakao.maps.ZoomControl();
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
	
	var clusterer = new kakao.maps.MarkerClusterer({
        map: map,					// 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true,		// 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        gridSize: 80,
        minLevel: 6, 				// 클러스터 할 최소 지도 레벨
        disableClickZoom: false,	// 클러스터 마커를 클릭했을 때 지도가 확대되도록 설정
        zIndex: 2
    });
    
    // cluster 최소 숫자 설정
	clusterer.setMinClusterSize(1);
	
	
	var markers = [];
	var chger = getchger(chger);
	var station = getstation(station);
	mkmarker(station,chger,map,markers,stationId);
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
		mkmarker(station,chger,map,markers,stationId);
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

function movemap(statid) {
	location.href ='map?statid='+statid;
}


$(document).ready(function(){
	var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
	var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
		return new bootstrap.Popover(popoverTriggerEl)
	})
	
	if(performance.getEntriesByType("navigation")[0].type == 'reload') {
		location.href = '/evcsmonitor/map';
	}
	// 지도를 표시할 div 선언
	var mapContainer = document.getElementById('map'),
	mapOption = { 
	    center: new kakao.maps.LatLng(37.55702316206885, 126.99452229408297),
	    level: 8
	};
	
	// 지도 생성
	var map = new kakao.maps.Map(mapContainer, mapOption);
	
	if (bmstation != null) {
		$('input[value="'+bmstation.bnm+'"]').prop('checked',true);
		ready(bmstation.lat,bmstation.lng,bmstation.statId,map);
	}else if (navigator.geolocation) { 
	    navigator.geolocation.getCurrentPosition(function(position){
			var lat = position.coords.latitude;
			var lng = position.coords.longitude;
			ready(lat,lng,'',map);
		});
	} else {
		var lat = 37.55702316206885;
		var lng = 126.99452229408297;
	    alert('사용자의 위치 정보를 받아올 수 없습니다.');
	    ready(lat,lng,'',map);
	}
	
	var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
	var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
		return new bootstrap.Popover(popoverTriggerEl)
	})
	
});
