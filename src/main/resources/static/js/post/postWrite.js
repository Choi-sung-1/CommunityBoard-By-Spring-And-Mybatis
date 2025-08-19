let map;
let marker = null;
const geocoder = new kakao.maps.services.Geocoder();
const currentAdd = document.getElementById('currentAddress');
let selectedAddress = '';

function showMap() {
    document.getElementById('container').hidden = false;
    document.getElementById('locDiv').hidden = true;

    const mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3
    };

    map = new kakao.maps.Map(document.getElementById('map'), mapOption);
    map.addControl(new kakao.maps.MapTypeControl(), kakao.maps.ControlPosition.TOPRIGHT);
    map.addControl(new kakao.maps.ZoomControl(), kakao.maps.ControlPosition.RIGHT);

    kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
        onMarker(mouseEvent.latLng);
    });

    getCurrentLocation();
}

function getCurrentLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            const userLatLng = new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude);
            map.setCenter(userLatLng);
            onMarker(userLatLng);
        }, function() {
            alert('위치 정보를 가져올 수 없습니다.');
        });
    } else {
        alert('브라우저가 위치 정보를 지원하지 않습니다.');
    }
}

function onMarker(coords) {
    if (marker) marker.setMap(null);
    marker = new kakao.maps.Marker({ position: coords });
    marker.setMap(map);
    map.setCenter(coords);
    showAddress(coords);
}

function showAddress(coords) {
    geocoder.coord2Address(coords.getLng(), coords.getLat(), function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            const roadAddress = result[0].road_address;
            const jibunAddress = result[0].address;
            selectedAddress = roadAddress ? '도로명주소 : ' + roadAddress.address_name :
                jibunAddress ? '지번주소 : ' + jibunAddress.address_name :
                    '주소 정보를 찾을 수 없습니다.';
            document.getElementById('result').innerText = selectedAddress;
        }
    });
}

function selectLocation() {
    currentAdd.innerText = selectedAddress;
    document.getElementById('postRegisterLocation').value = selectedAddress;
    document.getElementById('container').hidden = true;
    document.getElementById('locDiv').hidden = false;
}

function cancelLocation() {
    currentAdd.innerText = '';
    document.getElementById('container').hidden = true;
    document.getElementById('locDiv').hidden = false;
}

function previewImages(event) {
    const files = event.target.files;
    const previewCard = document.getElementById("previewCard");
    previewCard.innerHTML = "";

    Array.from(files).forEach(file => {
        const reader = new FileReader();
        reader.onload = function(e) {
            const img = document.createElement("img");
            img.src = e.target.result;
            img.alt = "첨부 이미지";
            previewCard.appendChild(img);
        };
        reader.readAsDataURL(file);
    });
}