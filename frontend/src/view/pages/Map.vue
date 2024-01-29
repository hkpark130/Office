<template>
    <div>
      <sdPageHeader title="장비 지도">
      </sdPageHeader>
      <Main>
        <a-row :gutter="25">
          <a-col :sm="24" :xs="24">
            <sdCards headless>
                <div id="map" style="width:800px;height:700px;"></div>
            </sdCards>
          </a-col>
        </a-row>
      </Main>
    </div>
</template>
  
<script>
  import { Main } from '../styled';
  import { defineComponent, ref } from 'vue';
  
  export default defineComponent({
    name: 'Map',
    components: {
      Main,
    },
    methods:{
        loadScript() {
            const script = document.createElement("script");
            script.src="//dapi.kakao.com/v2/maps/sdk.js?appkey="+process.env.KAKAO_KEY
            script.onload = () => window.kakao.maps.load(this.loadMap); 

            document.head.appendChild(script);
        },
        loadMap(latitude, longitude) {
            const container = document.getElementById("map");
            const options = {
                //좌표값 설정
                center: new window.kakao.maps.LatLng(latitude, longitude),
                level: 4
            };

            this.map = new window.kakao.maps.Map(container, options);
            this.loadMaker();
        },
        loadMaker(latitude, longitude) {
            const markerPosition = new window.kakao.maps.LatLng(latitude, longitude);

            const marker = new window.kakao.maps.Marker({
                position:markerPosition
            });

            marker.setMap(this.map);
        }
    },
    mounted() {
        const latitude = ref("37.39843974939604");
        const longitude = ref("127.10972941510465");

        if (window.kakao && window.kakao.maps) {
            this.loadMap(latitude.value, longitude.value);
        }  else {
            this.loadScript();
        }

        // var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
        // var options = { //지도를 생성할 때 필요한 기본 옵션
        //     center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
        //     level: 3 //지도의 레벨(확대, 축소 정도)
        // };

        // var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

        // return {
        //     map,
        // };
    },
  });
</script>
  