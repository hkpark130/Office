(function(){"use strict";var t={59992:function(t,e,o){var n=o(49963),a=o(66252);const r={key:0,class:"spin"},i={class:"spin"};function l(t,e,o,n,l,d){const p=(0,a.up)("a-spin"),s=(0,a.up)("router-view"),c=(0,a.up)("ThemeProvider");return t.isLoading?((0,a.wg)(),(0,a.iD)("div",r,[(0,a.Wm)(p)])):((0,a.wg)(),(0,a.j4)(c,{key:1,theme:{...t.theme,rtl:t.rtl,topMenu:t.topMenu,darkMode:t.darkMode}},{default:(0,a.w5)((()=>[((0,a.wg)(),(0,a.j4)(a.n4,null,{default:(0,a.w5)((()=>[(0,a.Wm)(s)])),fallback:(0,a.w5)((()=>[(0,a._)("div",i,[(0,a.Wm)(p)])])),_:1}))])),_:1},8,["theme"]))}var d=o(23481);const p="#5F63F2",s="#4347D9",c="#FF69A5",m="#E34A87",g="#1890ff",h="#0D79DF",x="rgba(0, 0, 0, 0.85)",u="#20C997",f="#0CAB7C",b="#FA8B0C",y="#D47407",w="#f5222d",k="#E30D0F",$="#2C99FF",v="#0D79DF",Z="#272B41",_="#131623",P="#5A5F7D",z="#363A51",S="#9299B8",F="#e2e6ea",j="#ffffff",D="#5A5F7D",C="#5A5F7D",E="#ADB4D2",B="#FF4D4F",L="#E30D0F",W="#F1F2F6",A="#E3E6EF",I="#C6D0DC",N="#EFF0F3",H="#F8F9FB",O="#F4F5F7",T="#868EAE",M="rgba(95,99,242,0.2)",U="#9299b8",q="#F63178",R="48px",V="36px",Y="29px",G={"primary-color":p,"primary-hover":s,"secondary-color":c,"secondary-hover":m,"link-color":g,"link-hover":h,"success-color":u,"success-hover":f,"warning-color":b,"warning-hover":y,"error-color":w,"error-hover":k,"info-color":$,"info-hover":v,"dark-color":Z,"dark-hover":_,"gray-color":P,"gray-hover":z,"light-color":S,"light-hover":F,"white-color":j,"white-hover":C,white:j,black:"#000",pink:q,"dash-color":D,"dash-hover":z,"extra-light-color":E,"danger-color":B,"danger-hover":L,"font-family":"'Inter', sans-serif","font-size-base":"14px","heading-color":x,"text-color":Z,"text-color-secondary":P,"disabled-color":"rgba(0, 0, 0, 0.25)","border-radius-base":"4px","border-color-base":"#d9d9d9","box-shadow-base":"0 2px 8px rgba(0, 0, 0, 0.15)","border-color-light":W,"border-color-normal":A,"border-color-deep":I,"bg-color-light":H,"bg-color-normal":O,"bg-color-deep":N,"light-gray-color":T,"gray-solid":U,"btn-height-large":R,"btn-height-small":V,"btn-height-extra-small":Y,"btn-default-color":Z,"card-head-background":"#ffffff","card-head-color":Z,"card-background":"#ffffff","card-head-padding":"16px","card-padding-base":"12px","card-radius":"10px","card-shadow":"0 5px 20px rgba(146,153,184,0.03)","layout-body-background":"#F4F5F7","layout-header-background":"#ffffff","layout-footer-background":"#fafafa","layout-header-height":"64px","layout-header-padding":"0 15px","layout-footer-padding":"24px 15px","layout-sider-background":"#ffffff","layout-trigger-height":"48px","layout-trigger-background":"#002140","layout-trigger-color":"#fff","layout-zero-trigger-width":"36px","layout-zero-trigger-height":"42px","layout-sider-background-light":"#fff","layout-trigger-background-light":"#fff","layout-trigger-color-light":"rgba(0, 0, 0, 0.65)","page-header-padding":"24px","page-header-padding-vertical":"16px","page-header-padding-breadcrumb":"12px","page-header-back-color":"#000","page-header-ghost-bg":"inherit","popover-color":Z,"alert-success-border-color":u,"alert-success-bg-color":u+15,"alert-error-bg-color":w+15,"alert-warning-bg-color":b+15,"alert-info-bg-color":$+15,"radio-button-checked-bg":p,"grid-gutter-width":25,"skeleton-color":W,"slider-rail-background-color":M,"slider-rail-background-color-hover":M,"slider-track-background-color":p,"slider-track-background-color-hover":p,"slider-handle-color":p,"slider-handle-size":"16px","input-height-base":"48px","input-border-color":A,"input-height-sm":"30px","input-height-lg":"50px","rate-star-color":b,"rate-star-size":"13px","switch-min-width":"30px","switch-sm-min-width":"30px","switch-height":"18px","switch-sm-height":"15px","result-title-font-size":"20px","result-subtitle-font-size":"12px","result-icon-font-size":"50px","tabs-horizontal-padding":"12px 15px","tabs-horizontal-margin":"0","list-item-padding":"10px 24px","tag-default-bg":"#EFF0F3","tag-default-color":Z,"tag-font-size":"11px"},K={...G,"primary-color":"red",backgroundColor:"#000"};var X=o(33907),J=(0,a.aZ)({name:"App",components:{ThemeProvider:d.f6},setup(){const{state:t}=(0,X.oR)(),e=(0,a.Fl)((()=>t.themeLayout.rtlData)),o=(0,a.Fl)((()=>t.themeLayout.loading)),n=(0,a.Fl)((()=>t.themeLayout.data)),r=(0,a.Fl)((()=>t.themeLayout.topMenu)),i=(0,a.Fl)((()=>t.firebase.loading));return(0,a.bv)((()=>{})),{theme:G,rtl:e,darkMode:n,topMenu:r,isLoading:o,isFbAuthLoading:i}}}),Q=o(83744);const tt=(0,Q.Z)(J,[["render",l]]);var et=tt;const ot=(0,n.ri)(et);var nt=ot,at=o(22201),rt=[{path:"/",alias:["/index.html"],name:"devicelist",component:()=>Promise.all([o.e(984),o.e(576)]).then(o.bind(o,29702))},{path:"/blank",name:"starter",component:()=>Promise.all([o.e(984),o.e(192)]).then(o.bind(o,59011))},{name:"add-approval",path:"/add-approval",component:()=>Promise.all([o.e(984),o.e(817)]).then(o.bind(o,56252))},{name:"forbidden",path:"/403",component:()=>Promise.all([o.e(984),o.e(27)]).then(o.bind(o,98032))},{path:"/:catchAll(.*)",component:()=>Promise.all([o.e(984),o.e(596)]).then(o.bind(o,69657))}];const it=[...rt];var lt=it;const dt=[{path:"register",name:"register",component:()=>o.e(685).then(o.bind(o,17532))},{path:"forgotPassword",name:"forgotPassword",component:()=>o.e(616).then(o.bind(o,76468))}];var pt=dt;const st=[{name:"Admin",path:"/",component:()=>Promise.all([o.e(10),o.e(328)]).then(o.bind(o,96305)),children:[...lt],meta:{auth:!1}},{name:"Auth",path:"/auth",component:()=>Promise.all([o.e(10),o.e(818)]).then(o.bind(o,78237)),children:[...pt],meta:{auth:!0}}],ct=(0,at.p7)({history:(0,at.PO)("/"),linkExactActiveClass:"active",routes:st});ct.beforeEach(((t,e,o)=>{o(),window.scrollTo(0,0)}));var mt=ct;const gt={darkMode:!1,topMenu:!1,rtl:!1,theme:G,darkTheme:K,auth0:!1};var ht=gt,xt={changeLayoutBegin(t){t.loading=!0},changeLayoutSuccess(t,e){t.loading=!1,t.data=e},changeLayoutErr(t,e){t.loading=!1,t.error=e},changeRtlBegin(t){t.loading=!0},changeRtlSuccess(t,e){t.loading=!1,t.rtlData=e},changeRtlErr(t,e){t.loading=!1,t.error=e},changeMenuBegin(t){t.loading=!0},changeMenuSuccess(t,e){t.loading=!1,t.topMenu=e},changeMenuErr(t,e){t.loading=!1,t.error=e}};const ut=()=>({data:ht.darkMode,rtlData:ht.rtl,topMenu:ht.topMenu,loading:!1,rtlLoading:!1,menuLoading:!1,error:null}),ft={async changeLayoutMode({commit:t},e){try{t("changeLayoutBegin"),setTimeout((()=>t("changeLayoutSuccess",e)),10)}catch(o){t("changeLayoutErr",o)}},async changeRtlMode({commit:t},e){try{t("changeRtlBegin"),setTimeout((()=>t("changeRtlSuccess",e)),10)}catch(o){t("changeRtlErr",o)}},async changeMenuMode({commit:t},e){try{t("changeMenuBegin"),setTimeout((()=>t("changeMenuSuccess",e)),10)}catch(o){t("changeMenuErr",o)}}};var bt={namespaced:!1,state:ut,actions:ft,mutations:xt},yt=JSON.parse('[{"id":1,"title":"AntDesign","count":10000},{"id":2,"title":"Design UI","count":10600},{"id":3,"title":"Bootstrap Design UI","count":60100},{"id":4,"title":"Meterial design","count":30010},{"id":5,"title":"AntDesign design language","count":100000}]'),wt={searchHeaderSuccess(t,e){e},searchHeaderErr(t,e){e}};const kt=()=>yt,$t={async headerSearchAction({commit:t},e){try{t("searchHeaderBegin");const o=yt.filter((t=>t.title.startsWith(e)));t("searchHeaderSuccess",o)}catch(o){t("searchHeaderErr",o)}}};var vt={namespaced:!1,state:kt,actions:$t,mutations:wt},Zt={loginBegin(t){t.login=!0},loginSuccess(t,e){t.loading=!1,t.login=e},loginErr(t,e){t.loading=!1,t.error=e},logoutBegin(t){t.loading=!0},logoutSuccess(t,e){t.loading=!1,t.login=e},logoutErr(t,e){t.loading=!1,t.error=e}},_t=o(31955);const Pt=()=>({login:_t.Z.get("logedIn"),loading:!1,error:null}),zt={async login({commit:t}){try{return t("loginBegin"),_t.Z.set("logedIn",!0),t("loginSuccess",!0)}catch(e){t("loginErr",e)}},async logOut({commit:t}){try{t("logoutBegin"),_t.Z.remove("logedIn"),t("logoutSuccess",null)}catch(e){t("logoutErr",e)}}};var St={namespaced:!1,state:Pt,actions:zt,mutations:Zt},Ft=(o(11611),o(70473)),jt={keycloakLoginBegin(t){t.loading=!0},keycloakLoginSuccess(t,e){t.loading=!1,t.isLogin=!0,t.uid=e},keycloakLoginErr(t,e){t.loading=!1,t.error=e},keycloakLogOutBegin(t){t.loading=!0},keycloakLogOutSuccess(t){t.loading=!1,t.isLogout=!0,t.isLogin=!1},keycloakLogOutErr(t,e){t.loading=!1,t.error=e}},Dt=o(94671);const Ct={flow:"standard",url:"http://192.168.2.111:8080",realm:"sso-test",clientId:"back-office",onLoad:"login-required",loadUserProfileAtStartUp:!0},Et=new Dt.Z(Ct);var Bt=Et;const Lt=t=>{Ft.Z.error({message:t})},Wt=()=>({data:null,loading:!1,isLogout:!1,isLogin:!1,error:!1,isSignUpError:!1,isSignUpLoading:!1,uid:!1}),At={async keycloakAuthLogin({commit:t},{data:e}){try{await t("keycloakLoginBegin"),await Bt.auth().signInWithEmailAndPassword(e.username,e.password),Bt.auth().onAuthStateChanged((e=>{e=e||{},t("keycloakLoginSuccess",!!e.uid&&e.uid)}))}catch(o){Lt(o.message),await t("keycloakLoginErr",o)}},async keycloakAuthGetUid({commit:t}){try{await t("keycloakLoginBegin"),Bt.auth().onAuthStateChanged((async e=>{e=e||{},await t("keycloakLoginSuccess",!!e.uid&&e.uid)}))}catch(e){await t("keycloakLoginErr",e)}},async keycloakAuthLogout({commit:t},e){try{await t("keycloakLogOutBegin"),await Bt.auth().signOut(),await t("keycloakLogOutSuccess",!1),await e()}catch(o){await t("keycloakLogOutErr",o)}}};var It={namespaced:!1,state:Wt,actions:At,mutations:jt},Nt=(0,X.MT)({modules:{themeLayout:bt,headerSearchData:vt,auth:St,keycloak:It}}),Ht=(o(33216),o(3613)),Ot=(o(94643),o(44648)),Tt=(o(80106),o(26558)),Mt=(o(85597),o(95146)),Ut=(o(93300),o(96461)),qt=(o(93589),o(61148)),Rt=(o(15921),o(87204)),Vt=(o(74457),o(30595)),Yt=(o(57986),o(29340)),Gt=(o(54346),o(97099)),Kt=(o(58569),o(40542)),Xt=(o(92591),o(41751)),Jt=(o(6788),o(97139)),Qt=(o(50460),o(31802)),te=(o(12777),o(9283)),ee=(o(7283),o(40904)),oe=(o(58517),o(66818)),ne=(o(84575),o(97142)),ae=(o(4148),o(9884)),re=(o(28633),o(9864)),ie=(o(16388),o(3107)),le=(o(84324),o(95878)),de=(o(50306),o(74889)),pe=(o(58258),o(88316)),se=(o(87483),o(773)),ce=(o(31529),o(46912)),me=(o(83616),o(38988)),ge=(o(36969),o(88943)),he=(o(17219),o(40885)),xe=(o(49282),o(47297)),ue=(o(17198),o(59418)),fe=(o(21723),o(85263)),be=(o(38770),o(47346)),ye=(o(91019),o(46978)),we=(o(5378),o(72616)),ke=(o(67485),o(6723)),$e=(o(44830),o(27782)),ve=(o(20446),o(16987)),Ze=(o(79861),o(25808)),_e=(o(89397),o(98704)),Pe=(o(7163),o(75356)),ze=(o(11838),o(6329)),Se=(o(38049),o(99230)),Fe=(o(88124),o(65494)),je=(o(51998),o(24871)),De=(o(46724),o(14563)),Ce=(o(63197),o(172)),Ee=(o(82577),o(29937)),Be=(o(76266),o(52245)),Le=(o(65057),o(9179)),We=(o(80149),o(45949)),Ae=(o(58856),o(69662)),Ie=(o(58293),o(45542)),Ne=(o(94310),o(61446));nt.config.globalProperties.$message=Ne.ZP,nt.use(Ie.Z),nt.use(Ae.Z),nt.use(We.Z),nt.use(Le.ZP),nt.use(Be["default"]),nt.use(Ee.ZP),nt.use(Ce.Z),nt.use(De.Z),nt.use(je.Z),nt.use(Fe.Z),nt.use(Se.ZP),nt.use(ze.Z),nt.use(Pe.ZP),nt.use(_e.Z),nt.use(Ze.ZP),nt.use(ve.Z),nt.use($e.ZP),nt.use(ke.ZP),nt.use(we.Z),nt.use(ye.Z),nt.use(be.ZP),nt.use(fe.Z),nt.use(ue.ZP),nt.use(xe.Z),nt.use(he.Z),nt.use(ge.Z),nt.use(me.ZP),nt.use(ce.ZP),nt.use(se.ZP),nt.use(pe.Z),nt.use(de.Z),nt.use(le.ZP),nt.use(ie.C),nt.use(re.ZP),nt.use(ae.Z),nt.use(ne.ZP),nt.use(oe.Z),nt.use(ee.Z),nt.use(te.ZP),nt.use(Qt.ZP),nt.use(Jt.Z),nt.use(Xt.Z),nt.use(Kt.ZP),nt.use(Gt.ZP),nt.use(Yt.Z),nt.use(Vt.ZP),nt.use(Rt.Z),nt.use(Ce.Z),nt.use(qt.ZP),nt.use(Ut.ZP),nt.use(Mt.ZP),nt.use(Tt.Z),nt.use(Ot.ZP),nt.use(Ht.ZP);var He=o(78947),Oe=o(51436),Te=o(57810);He.vI.add(Oe.mRB),nt.component("font-awesome-icon",Te.GN);var Me=o(3577);const Ue={key:1};function qe(t,e,o,n,r,i){const l=(0,a.up)("sdFeatherIcons"),d=(0,a.up)("router-link"),p=(0,a.up)("sdDropdown"),s=(0,a.up)("CardFrame"),c=(0,a.up)("sdHeading");return t.headless?((0,a.wg)(),(0,a.j4)(s,{key:1,bodypadding:t.bodypadding&&t.bodypadding,bodyStyle:t.bodyStyle&&t.bodyStyle,size:t.size,style:{width:"100%"},bordered:t.border},{title:(0,a.w5)((()=>[(0,a.WI)(t.$slots,"title")])),default:(0,a.w5)((()=>[t.title?((0,a.wg)(),(0,a.j4)(c,{key:0,as:"h4"},{default:(0,a.w5)((()=>[(0,a.Uk)((0,Me.zw)(t.title),1)])),_:1})):(0,a.kq)("",!0),t.caption?((0,a.wg)(),(0,a.iD)("p",Ue,(0,Me.zw)(t.caption),1)):(0,a.kq)("",!0),(0,a.WI)(t.$slots,"default")])),_:3},8,["bodypadding","bodyStyle","size","bordered"])):((0,a.wg)(),(0,a.j4)(s,{key:0,size:t.size,title:t.title,bodyStyle:t.bodyStyle&&t.bodyStyle,headStyle:t.headStyle&&t.headStyle,bordered:t.border,bodypadding:t.bodypadding&&t.bodypadding,style:{width:"100%"}},{title:(0,a.w5)((()=>[(0,a.WI)(t.$slots,"title")])),extra:(0,a.w5)((()=>[t.more?((0,a.wg)(),(0,a.j4)(p,{key:0,action:["click"],placement:"bottomRight"},{overlay:(0,a.w5)((()=>[(0,a.WI)(t.$slots,"more")])),default:(0,a.w5)((()=>[t.moreText?((0,a.wg)(),(0,a.j4)(d,{key:1,to:"#"},{default:(0,a.w5)((()=>[(0,a.Uk)("More")])),_:1})):((0,a.wg)(),(0,a.j4)(d,{key:0,to:"#"},{default:(0,a.w5)((()=>[(0,a.Wm)(l,{type:"more-horizontal",size:"24"})])),_:1}))])),_:3})):(0,a.kq)("",!0),(0,a.WI)(t.$slots,"button")])),default:(0,a.w5)((()=>[(0,a.WI)(t.$slots,"caption"),(0,a.WI)(t.$slots,"default")])),_:3},8,["size","title","bodyStyle","headStyle","bordered","bodypadding"]))}const Re=t=>`\n    .ant-card-head {\n      display: flex;\n      justify-content: space-evenly;\n      .ant-card-head-wrapper{\n        width: 100%;\n      }\n      .ant-tabs.ant-tabs-top.ant-card-head-tabs.ant-tabs-large.ant-tabs-line{\n        width: 100%;\n        display: block;\n      }\n      .ant-tabs-nav-scroll {\n        display: flex;\n        justify-content: flex-end;\n      }\n      .ant-tabs-ink-bar.ant-tabs-ink-bar-animated {\n        display: none;\n      }\n      .ant-tabs-nav .ant-tabs-tab {\n        border-radius: 2px;\n        height: 30px;\n        margin: 0px;\n        ${t.rtl?"margin-right":"margin-left"}: -1px;\n        display: inline-flex;\n        align-items: center;\n        border: 1px solid ${t["border-color-base"]};\n        background: #fff;\n        span svg {\n          ${t.rtl?"padding-right":"padding-left"}: 5px;\n        }\n      }\n      .ant-tabs-nav .ant-tabs-tab-active {\n        font-weight: 500;\n        border: 1px solid ${t["primary-color"]};\n        background: ${t["primary-color"]};\n        color: #fff;\n        border-radius: 2px;\n        height: 30px;\n        margin: 0px;\n        display: inline-flex;\n        align-items: center;\n      }\n      .ant-tabs-bar {\n        border: none;\n      }\n      .ant-tabs-nav-wrap {\n        margin-bottom: 0px;\n        overflow: hidden;\n    }\n    }\n    .ant-card-head .ant-tabs.ant-tabs-top.ant-card-head-tabs.ant-tabs-large.ant-tabs-line {\n      width: 100%;\n      display: block;\n      justify-content: flex-end;\n      height: 73px;\n      padding-top: 8px;\n  }\n`,Ve=["isbutton","bodypadding"],Ye=(0,d.ZP)(le.ZP,Ve)`
  ${({isbutton:t,theme:e})=>t&&Re(e)}

  margin-bottom: 25px;
  .ant-card-head{
    border-color: #F1F2F6;
  }
  .ant-card-head .ant-card-head-title {
    padding: 16.5px 0;
    line-height: 1.4;
  }
  .ant-card-head-title .ant-page-header-heading-title{
    font-weight: 500;
  }
  .ant-card-body{
    padding: ${({bodypadding:t})=>t?`${t}`:"25px"};
    table{
      .ant-tag{
        line-height: 18px;
        border: 0 none;
        text-transform: uppercase;
        font-size: 10px;
        color: #fff;
        &.early{
          background: ${({theme:t})=>t["primary-color"]};
        }
        &.late{
          background: ${({theme:t})=>t["warning-color"]};
        }
        &.complete{
          background: ${({theme:t})=>t["success-color"]};
        }
        &.progress{
          background: ${({theme:t})=>t["danger-color"]};
        }
      }
    }
  }
  .ant-card.ant-card-bordered {
      border-radius: 4px;
      overflow: hidden;
      border: none;
  }
  .custom-label {
    font-size: 13px;
    color: #868eae;
    display: inline-flex;
    align-items: center;
  }
  .custom-label:not(:last-child) {
    ${({theme:t})=>t.rtl?" margin-left":" margin-right"} : 20px;
  }

  .custom-label span {
    width: 8px;
    height: 8px;
    border-radius: 50%;
    ${({theme:t})=>t.rtl?" margin-left":" margin-right"}: 7px;
  }


  .ant-card-extra .ant-dropdown-trigger {
    line-height: 0;
    order: 1;
    ${({theme:t})=>t.rtl?" margin-right":" margin-left"}: 20px;
  }

  .growth-downward h1 sub,
  .growth-upward h1 sub {
    font-size: 14px;
    font-weight: 600;
    bottom: 0;
    ${({theme:t})=>t.rtl?" right":" left"}: 5px;
  }

`;var Ge=o(5298),Ke=(0,a.aZ)({name:"Cards",components:{CardFrame:Ye},props:{title:Ge.ZP.oneOfType([Ge.ZP.string,Ge.ZP.object,Ge.ZP.node]),size:Ge.ZP.oneOf(["default","small"]).def("default"),more:Ge.ZP.bool.def(!1),bodyStyle:Ge.ZP.object,headStyle:Ge.ZP.object,headless:Ge.ZP.bool.def(!1),border:Ge.ZP.bool.def(!1),caption:Ge.ZP.string,bodypadding:Ge.ZP.string,moreText:Ge.ZP.bool.def(!1)}});const Xe=(0,Q.Z)(Ke,[["render",qe]]);var Je=Xe;const Qe=["innerHTML"];function to(t,e,o,n,r,i){return(0,a.wg)(),(0,a.iD)("i",{class:(0,Me.C_)(t.classes),innerHTML:t.activity,style:{"line-height":"0"}},null,10,Qe)}var eo=o(28508),oo=o(2262),no=(0,a.aZ)({name:"FeatherIcons",props:{type:Ge.ZP.string.def("activity"),size:Ge.ZP.oneOfType([Ge.ZP.number,Ge.ZP.string]).def(16),stroke:Ge.ZP.oneOfType([Ge.ZP.number,Ge.ZP.string]).def(2),classes:Ge.ZP.string,color:Ge.ZP.string,fill:Ge.ZP.string.def("transparent")},setup(t){const{size:e,color:o,fill:n,type:r,stroke:i,classes:l}=(0,oo.BK)(t);let d=(0,oo.iH)(eo.icons[r.value].toSvg({"stroke-width":i.value,width:e.value,height:e.value,color:o.value,fill:n.value,class:l.value}));return(0,a.m0)((()=>{d.value=eo.icons[r.value].toSvg({"stroke-width":i.value,width:e.value,height:e.value,color:o.value,fill:n.value})})),{activity:d}}});const ao=(0,Q.Z)(no,[["render",to]]);var ro=ao;function io(t,e,o,n,r,i){const l=(0,a.up)("ButtonStyled");return(0,a.wg)(),(0,a.j4)(l,{squared:t.squared,outlined:t.outlined,ghost:t.ghost,transparent:t.transparented,raised:t.raised,data:t.type,size:t.size,shape:t.shape,type:t.type,icon:t.icon,color:t.color,social:t.social,click:()=>t.load&&t.enterLoading,loading:t.loading,block:t.block,disabled:t.disabled,class:(0,Me.C_)(`ant-btn ant-btn-${t.type} ${t.shape&&`ant-btn-${t.shape}`} ${t.block&&"ant-btn-block"}`)},{default:(0,a.w5)((()=>[(0,a.WI)(t.$slots,"default")])),_:3},8,["squared","outlined","ghost","transparent","raised","data","size","shape","type","icon","color","social","click","loading","block","disabled","class"])}const lo=We.Z.Group,po=["type","shape","icon","size","outlined","ghost","transparent","raised","squared","color","social","loading"],so=(t,e)=>`\n        background: transparent;\n        border: 1px ${"dash"!==e?"solid":"dashed"} ${"light"!==e?t[`${e}-color`]:t["border-color-normal"]};\n        color: ${"default"!==e&&t[`${e}-color`]};\n  \n        &:hover, &:focus {\n          background: transparent;\n          border: 1px ${"dash"!==e?"solid":"dashed"} ${"default"!==e&&t[`${e}-hover`]};\n          color: ${"default"!==e&&t[`${e}-hover`]};\n        }\n    `,co=t=>`\n          background: transparent;\n          border: 1px solid ${t["border-color-normal"]} !important;\n          color: ${t["border-color-normal"]} !important;\n\n          &:hover, &:focus {\n              background: #ffffff50 !important;\n              border: 1px solid transparent !important;\n              color: ${t["border-color-normal"]} !important;\n          }\n      `,mo=(t,e)=>`\n        background: ${"default"!==e&&t[`${e}-color`]}15;\n        border-width: 0px;\n        color: ${"default"!==e&&t[`${e}-color`]};\n        &:hover, &:focus {\n            background: ${"default"!==e&&t[`${e}-hover`]}15;\n            border-width: 0px;\n            color: ${"default"!==e&&t[`${e}-hover`]}; \n        }\n    `,go=(t,e)=>`\n        box-shadow: 0 10px 15px ${"white"!==e?t[`${e}-color`]:"#9299B8"}20;\n    `,ho=(t,e)=>`\n      background: ${"default"!==e&&t[`${e}-color`]};\n      border: 1px solid ${"default"!==e?t[`${e}-color`]:t["disabled-color"]};\n      color: ${"default"!==e&&"#ffffff"};\n      border-radius: 0px;\n      padding: 0px 15px;\n  \n      &:hover, &:focus {\n          background: ${"default"!==e&&t[`${e}-hover`]};\n          border: 1px solid ${"default"!==e&&t[`${e}-hover`]};\n          color: ${"default"!==e&&"#ffffff"};\n      }\n  `,xo=(t,e)=>`\n      background: transparent;\n      border: 1px ${"dash"!==e?"solid":"dashed"} ${"default"!==e?t[`${e}-color`]:t["disabled-color"]};\n      color: ${"default"!==e&&t[`${e}-color`]};\n      border-radius: 0px;\n      padding: 0px 15px;\n      &:hover, &:focus {\n          background: ${"default"!==e&&t[`${e}-hover`]};\n          border: 1px solid ${"default"!==e&&t[`${e}-hover`]};\n          color: ${"default"!==e&&"#ffffff"};\n      }\n  `,uo=(t,e)=>`\n      background: ${t};\n      background: ${t};\n      border: 1px solid ${t};\n      color: #ffffff;\n      border-radius: ${e?"40px":"4px"};\n      padding: 0px 12px;\n      display: inline-flex;\n      align-items: center;\n      span {\n          padding-left: 5px;\n      }\n      &:hover, &:focus {\n          background: ${t}90;\n          border: 1px solid ${t}90;\n          color: #ffffff;\n      }\n  `,fo=(0,d.ZP)("button",po)`

    background: ${({type:t,theme:e})=>"default"!==t&&e[`${t}-color`]+""};
    border-width: 0px;
    border-style: ${({type:t})=>"dashed"!==t?"solid":"dashed"};
    color: ${({type:t})=>"default"!==t&&"white"!==t?"#ffffff":"#5A5F7D"};
    display: inline-flex;
    align-items: center;
    justify-content: center;
    border-radius: ${({shape:t})=>t?"40px":"5px"};
    padding: 0px 20.5px;
    height: ${({size:t,theme:e})=>"default"!==t?e[`btn-height-${t}`]:"42px"};
    font-weight: 500;
    box-shadow: 0 0;
    &:hover, &:focus {
        background: ${({type:t,theme:e})=>"default"!==t&&"white"!==t&&e[`${t}-hover`]+""};
        color: ${({type:t})=>"default"!==t&&"white"!==t?"#ffffff":"#5A5F7D"};
    }
    i,
    svg,
    img{
        width: 16px;
        height: 16px;
    }

    span{
        ${({theme:t})=>t.rtl?"margin-right":"margin-left"}: 6px
    }

    ${({transparent:t,theme:e,type:o})=>t&&mo(e,o)};
    ${({outlined:t,theme:e,type:o})=>t&&so(e,o)};
    ${({ghost:t,theme:e})=>t&&co(e)};
    ${({raised:t,theme:e,type:o})=>t&&go(e,o)};
    ${({squared:t,theme:e,type:o})=>t&&ho(e,o)};
    ${({squared:t,outlined:e,theme:o,type:n})=>t&&e&&xo(o,n)};
    ${({social:t,color:e,shape:o})=>t&&uo(e,o)};
`,bo=(0,d.ZP)(lo)`
    >.ant-btn:first-child{
        border-top-left-radius: 3px !important;
        border-bottom-left-radius: 3px !important;
    }
    button {
        margin: 0px;
        padding: 0 10.75px;
        height: 30px;
        font-size: 12px;
        font-weight: 500;        
    }
    .ant-btn-light:hover{
        background: #F4F5F7;
    }
`,yo=(0,d.ZP)("div")`
    position: relative;
    .sDash-range-calendar{
        position: absolute;
        padding: 10px 10px 0 10px;
        z-index: 22 !important;
        top: 50px;
        right: 0;
        margin-right: -50%;
        background-color: #fff;
    }
`;var wo=(0,a.aZ)({name:"Button",components:{ButtonStyled:fo},setup(){let t=!1;const e=function(){t=!0};return{loading:t,enterLoading:e}},props:{type:Ge.ZP.oneOf(["primary","secondary","success","info","warning","danger","link","dark","light","white","dashed","error","default"]).def("default"),shape:Ge.ZP.string,icon:Ge.ZP.string,size:Ge.ZP.oneOf(["large","default","small"]).def("default"),color:Ge.ZP.string,outlined:Ge.ZP.bool.def(!1),block:Ge.ZP.bool.def(!1),disabled:Ge.ZP.bool.def(!1),transparented:Ge.ZP.bool.def(!1),raised:Ge.ZP.bool.def(!1),squared:Ge.ZP.bool.def(!1),social:Ge.ZP.bool.def(!1),load:Ge.ZP.bool.def(!1),ghost:Ge.ZP.bool.def(!1)}});const ko=(0,Q.Z)(wo,[["render",io]]);var $o=ko;function vo(t,e,o,n,r,i){const l=(0,a.up)("ButtonStyledGroup");return(0,a.wg)(),(0,a.j4)(l,null,{default:(0,a.w5)((()=>[(0,a.WI)(t.$slots,"default")])),_:3})}var Zo=(0,a.aZ)({name:"BtnGroup",components:{ButtonStyledGroup:bo}});const _o=(0,Q.Z)(Zo,[["render",vo]]);var Po=_o;const zo={key:0,class:"sDash-range-calendar",placement:"bottomRight",title:"Search by Calendar",action:"click"},So=(0,a._)("span",null,"Calendar",-1);function Fo(t,e,o,n,r,i){const l=(0,a.up)("v-calendar"),d=(0,a.up)("sdFeatherIcons"),p=(0,a.up)("sdButton"),s=(0,a.up)("CustomDropDown");return(0,a.wg)(),(0,a.j4)(s,{id:"dropdownParent"},{default:(0,a.w5)((()=>[t.isVisible?((0,a.wg)(),(0,a.iD)("div",zo,[(0,a.Wm)(l,{columns:t.$screens({default:1,lg:2}),"is-expanded":"",modelValue:t.range,"onUpdate:modelValue":e[0]||(e[0]=e=>t.range=e),"is-range":""},null,8,["columns","modelValue"])])):(0,a.kq)("",!0),(0,a.Wm)(p,{onClick:t.handleVisible,size:"small",type:"white"},{default:(0,a.w5)((()=>[(0,a.Wm)(d,{type:"calendar",size:"14"}),So])),_:1},8,["onClick"])])),_:1})}var jo=(0,a.aZ)({name:"CalendarButton",components:{CustomDropDown:yo},setup(){const t=(0,oo.iH)(!1),e=()=>{t.value=!t.value},o={start:new Date(2021,0,1),end:new Date(2021,0,5)};return(0,a.bv)((()=>{document.body.addEventListener("click",(e=>{if(e.target.closest("#dropdownParent"))return!1;t.value=!1}))})),{handleVisible:e,isVisible:t,range:o}}});const Do=(0,Q.Z)(jo,[["render",Fo]]);var Co=Do;const Eo={to:"#"},Bo=(0,a._)("span",null,"Printer",-1),Lo={to:"#"},Wo=(0,a._)("span",null,"PDF",-1),Ao={to:"#"},Io=(0,a._)("span",null,"Google Sheets",-1),No={to:"#"},Ho=(0,a._)("span",null,"Excel (XLSX)",-1),Oo={to:"#"},To=(0,a._)("span",null,"CSV",-1),Mo=(0,a._)("span",null,"Export",-1);function Uo(t,e,o,n,r,i){const l=(0,a.up)("sdFeatherIcons"),d=(0,a.up)("sdButton"),p=(0,a.up)("sdPopover");return(0,a.wg)(),(0,a.j4)(p,{placement:"bottomLeft",action:"click"},{content:(0,a.w5)((()=>[(0,a._)("a",Eo,[(0,a.Wm)(l,{type:"printer"}),Bo]),(0,a._)("a",Lo,[(0,a.Wm)(l,{type:"book-open"}),Wo]),(0,a._)("a",Ao,[(0,a.Wm)(l,{type:"file-text"}),Io]),(0,a._)("a",No,[(0,a.Wm)(l,{type:"x"}),Ho]),(0,a._)("a",Oo,[(0,a.Wm)(l,{type:"file"}),To])])),default:(0,a.w5)((()=>[(0,a.Wm)(d,{size:"small",type:"white"},{default:(0,a.w5)((()=>[(0,a.Wm)(l,{type:"download"}),Mo])),_:1})])),_:1})}var qo=(0,a.aZ)({name:"ExportButton",components:{}});const Ro=(0,Q.Z)(qo,[["render",Uo]]);var Vo=Ro;const Yo={to:"#"},Go=(0,a._)("span",null,"Facebook",-1),Ko={to:"#"},Xo=(0,a._)("span",null,"Twitter",-1),Jo={to:"#"},Qo=(0,a._)("span",null,"Feed",-1),tn={to:"#"},en=(0,a._)("span",null,"Linkedin",-1),on={to:"#"},nn=(0,a._)("span",null,"Instagram",-1),an=(0,a._)("span",null,"Share",-1);function rn(t,e,o,n,r,i){const l=(0,a.up)("sdFeatherIcons"),d=(0,a.up)("sdButton"),p=(0,a.up)("sdPopover");return(0,a.wg)(),(0,a.j4)(p,{placement:"bottomLeft",action:"click"},{content:(0,a.w5)((()=>[(0,a._)("a",Yo,[(0,a.Wm)(l,{type:"facebook"}),Go]),(0,a._)("a",Ko,[(0,a.Wm)(l,{type:"twitter"}),Xo]),(0,a._)("a",Jo,[(0,a.Wm)(l,{type:"rss"}),Qo]),(0,a._)("a",tn,[(0,a.Wm)(l,{type:"linkedin"}),en]),(0,a._)("a",on,[(0,a.Wm)(l,{type:"instagram"}),nn])])),default:(0,a.w5)((()=>[(0,a.Wm)(d,{size:"small",type:"white"},{default:(0,a.w5)((()=>[(0,a.Wm)(l,{type:"share-2",size:"14"}),an])),_:1})])),_:1})}var ln=(0,a.aZ)({name:"ShareButton",components:{}});const dn=(0,Q.Z)(ln,[["render",rn]]);var pn=dn;const sn=(0,d.ZP)(pe.Z)`
  margin-bottom: 0;

  .page-header-actions button.ant-btn-white svg {
    width: 12px;
    height: 12px;
    ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 2px;
    color: #5f63f2;
  }
  i +span, svg +span, img +span {
      ${({theme:t})=>t.rtl?"margin-right":"margin-left"}: 6px;
  }
`,cn=(0,a.aZ)({components:{PageHeaderStyle:sn},props:{title:Ge.ZP.oneOfType([Ge.ZP.string,Ge.ZP.object]),subTitle:Ge.ZP.oneOfType([Ge.ZP.string,Ge.ZP.object]),bgColor:Ge.ZP.string,class:Ge.ZP.string,routes:Ge.ZP.object,buttons:Ge.ZP.array,ghost:Ge.ZP.bool,breadcrumb:Ge.ZP.object},render(){return(0,a.Wm)("div",null,[(0,a.Wm)("div",{style:{backgroundColor:this.bgColor||"#F4F5F7"}},[(0,a.Wm)(sn,{style:{backgroundColor:"rgb(244, 245, 247)"},class:this.class,title:this.title||this.$slots.title(),subTitle:this.subTitle||(this.$slots.subTitle?this.$slots.subTitle():null),breadcrumb:this.breadcrumb&&this.breadcrumb,ghost:this.ghost,extra:this.$slots.buttons&&this.$slots.buttons()},null)])])}});function mn(t,e,o,n,r,i){const l=(0,a.up)("H1"),d=(0,a.up)("H2"),p=(0,a.up)("H3"),s=(0,a.up)("H4"),c=(0,a.up)("H5"),m=(0,a.up)("H6");return"H1"===t.as.toUpperCase()?((0,a.wg)(),(0,a.j4)(l,{key:0,style:(0,Me.j5)(t.styles),className:t.className,id:t.id},{default:(0,a.w5)((()=>[(0,a.WI)(t.$slots,"default")])),_:3},8,["style","className","id"])):"H2"===t.as.toUpperCase()?((0,a.wg)(),(0,a.j4)(d,{key:1,style:(0,Me.j5)(t.styles),className:t.className,id:t.id},{default:(0,a.w5)((()=>[(0,a.WI)(t.$slots,"default")])),_:3},8,["style","className","id"])):"H3"===t.as.toUpperCase()?((0,a.wg)(),(0,a.j4)(p,{key:2,style:(0,Me.j5)(t.styles),className:t.className,id:t.id},{default:(0,a.w5)((()=>[(0,a.WI)(t.$slots,"default")])),_:3},8,["style","className","id"])):"H4"===t.as.toUpperCase()?((0,a.wg)(),(0,a.j4)(s,{key:3,style:(0,Me.j5)(t.styles),className:t.className,id:t.id},{default:(0,a.w5)((()=>[(0,a.WI)(t.$slots,"default")])),_:3},8,["style","className","id"])):"H5"===t.as.toUpperCase()?((0,a.wg)(),(0,a.j4)(c,{key:4,style:(0,Me.j5)(t.styles),className:t.className,id:t.id},{default:(0,a.w5)((()=>[(0,a.WI)(t.$slots,"default")])),_:3},8,["style","className","id"])):((0,a.wg)(),(0,a.j4)(m,{key:5,style:(0,Me.j5)(t.styles),className:t.className,id:t.id},{default:(0,a.w5)((()=>[(0,a.WI)(t.$slots,"default")])),_:3},8,["style","className","id"]))}const gn=d.ZP.h1`
  font-size: 30px;
  font-weight: 600;
  line-height: 38px;
`,hn=d.ZP.h1`
  font-size: 24px;
  font-weight: 600;
  line-height: 30px;
`,xn=d.ZP.h1`
  font-size: 22px;
  font-weight: 600;
  line-height: 27px;
`,un=d.ZP.h1`
  font-size: 20px;
  font-weight: 600;
  line-height: 24px;
`,fn=d.ZP.h1`
  font-size: 18px;
  font-weight: 600;
  line-height: 22px;
`,bn=d.ZP.h1`
  font-size: 16px;
  font-weight: 600;
  line-height: 20px;
`;var yn=(0,a.aZ)({name:"Heading",props:{as:Ge.ZP.oneOf(["h1","h2","h3","h4","h5","h6"]).def("h1"),className:Ge.ZP.string,id:Ge.ZP.string,styles:Ge.ZP.object.def({})},components:{H1:gn,H2:hn,H3:xn,H4:un,H5:fn,H6:bn}});const wn=(0,Q.Z)(yn,[["render",mn]]);var kn=wn;function $n(t,e,o,n,r,i){const l=(0,a.up)("a-select-option"),d=(0,a.up)("AutoCompleteStyled"),p=(0,a.up)("sdFeatherIcons"),s=(0,a.up)("sdButton"),c=(0,a.up)("a-input");return t.customComponent?((0,a.wg)(),(0,a.j4)(d,{key:0,style:(0,Me.j5)({width:t.width}),onSelect:t.onSelect,onSearch:t.onSearching},(0,a.Nv)({default:(0,a.w5)((()=>[(0,a.WI)(t.$slots,"default")])),_:2},[t.myData.length?{name:"options",fn:(0,a.w5)((()=>[((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(t.myData,(t=>((0,a.wg)(),(0,a.j4)(l,{key:t.title},{default:(0,a.w5)((()=>[(0,a.Uk)((0,Me.zw)(t.title),1)])),_:2},1024)))),128))])),key:"0"}:{name:"options",fn:(0,a.w5)((()=>[(0,a.Wm)(l,{key:"Data not found!"},{default:(0,a.w5)((()=>[(0,a.Uk)(" Data not found! ")])),_:1})])),key:"1"}]),1032,["style","onSelect","onSearch"])):t.patterns?((0,a.wg)(),(0,a.j4)(d,{key:1,value:t.value,"onUpdate:value":e[0]||(e[0]=e=>t.value=e),class:"certain-category-search",dropdownClassName:"certain-category-search-dropdown",dropdownMatchSelectWidth:!1,dropdownStyle:{width:300},style:(0,Me.j5)({width:t.width}),placeholder:t.placeholder,onSearch:t.onSearching},(0,a.Nv)({default:(0,a.w5)((()=>[(0,a.Wm)(c,null,{suffix:(0,a.w5)((()=>[t.patternButtons?((0,a.wg)(),(0,a.j4)(s,{key:0,class:"search-btn",style:(0,Me.j5)({[t.rtl?"marginLeft":"marginRight"]:-20}),type:"primary"},{default:(0,a.w5)((()=>[(0,a.Wm)(p,{type:"search"})])),_:1},8,["style"])):((0,a.wg)(),(0,a.j4)(p,{key:1,type:"search"}))])),_:1})])),_:2},[t.myData.length?{name:"options",fn:(0,a.w5)((()=>[((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(t.myData,(t=>((0,a.wg)(),(0,a.j4)(l,{key:t.title},{default:(0,a.w5)((()=>[(0,a.Uk)((0,Me.zw)(t.title),1)])),_:2},1024)))),128))])),key:"0"}:{name:"options",fn:(0,a.w5)((()=>[(0,a.Wm)(l,{key:"Data not found!"},{default:(0,a.w5)((()=>[(0,a.Uk)(" Data not found! ")])),_:1})])),key:"1"}]),1032,["value","style","placeholder","onSearch"])):((0,a.wg)(),(0,a.j4)(d,{key:2,style:(0,Me.j5)({width:t.width}),onSelect:t.onSelect,onSearch:t.onSearching,placeholder:t.placeholder,value:t.value},(0,a.Nv)({_:2},[t.myData.length?{name:"options",fn:(0,a.w5)((()=>[((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(t.myData,(t=>((0,a.wg)(),(0,a.j4)(l,{key:t.title},{default:(0,a.w5)((()=>[(0,a.Uk)((0,Me.zw)(t.title),1)])),_:2},1024)))),128))])),key:"0"}:{name:"options",fn:(0,a.w5)((()=>[(0,a.Wm)(l,{key:"Data not found!"},{default:(0,a.w5)((()=>[(0,a.Uk)(" Data not found! ")])),_:1})])),key:"1"}]),1032,["style","onSelect","onSearch","placeholder","value"]))}const vn=["customComponent","patterns","patternButtons","width","onSearch","options","placeholder"],Zn=(0,d.ZP)(Pe.ZP,vn)`
    display: block !important;
    .ant-select-selection-placeholder{
        padding: 0 20px !important;
        text-align: ${({theme:t})=>t.rtl?"right":"left"};
        ${({theme:t})=>t.rtl?"right":"left"}: 2px !important;
    }
    .ant-select-selector{
        display: flex;
        align-items: center;
        padding: 0 !important;
        border-color: #E3E6EF !important;
        .ant-select-selection-search{
            .ant-input-affix-wrapper{
                padding: 0 20px;
            }
            .ant-input-suffix{
                svg,
                i{
                    position: relative;
                    /* top: 1.5px; */
                    color: ${({theme:t})=>t["extra-light-color"]};
                }
            }
        }
    }
    .ant-select-selector input{
        height: 35px !important;
        ${({theme:t})=>t.rtl?"padding-right":"padding-left"}: 0 !important;
        &::placeholder{
            color: ${({theme:t})=>t["extra-light-color"]} !important;
        }
    }
    .ant-select-selection-search{
        ${({theme:t})=>t.rtl?"right":"left"}: 20px;
        width: auto !important;
        @media only screen and (max-width: 575px){
            width: 100% !important;
        }
    }
    .ant-select-selection-search textarea.ant-input{
        padding: 12px 20px;
        height: 50px;
    }
    .ant-input-affix-wrapper{
        padding: 0 20px;
    }
    .ant-input-affix-wrapper input{
        height: 38px !important;
    }
    .ant-input-affix-wrapper .ant-input-suffix .search-btn{
        height: 38px;
        border-radius: ${({theme:t})=>t.rtl?"4px 0 0 4px":"0 4px 4px 0"};
        svg,
        i{
            color: #fff;
        }
    }
`;var _n=(0,a.aZ)({name:"AutoComplete",components:{AutoCompleteStyled:Zn},props:{customComponent:Ge.ZP.bool.def(!1),patterns:Ge.ZP.bool.def(!1),patternButtons:Ge.ZP.bool.def(!1),width:Ge.ZP.string.def("350px"),dataSource:Ge.ZP.array,placeholder:Ge.ZP.string.def("Input here")},setup(t,{emit:e}){const{dataSource:o}=(0,oo.BK)(t),{state:n}=(0,X.oR)(),r=(0,oo.iH)(""),i=(0,oo.iH)(o.value),l=(0,a.Fl)((()=>n.themeLayout.rtlData)),d=t=>{r.value=t;const n=o.value.filter((e=>e.title.toUpperCase().startsWith(t.toUpperCase())));return e("onSearch",t),i.value=t?n:o.value},p=()=>{};return{value:r,myData:i,rtl:l,onSearching:d,onSelect:p}}});const Pn=(0,Q.Z)(_n,[["render",$n]]);var zn=Pn;const Sn=(0,a._)("a",{to:"#"},[(0,a._)("span",null,"Export to CSV")],-1),Fn=(0,a._)("a",{to:"#"},[(0,a._)("span",null,"Export to XML")],-1),jn=(0,a._)("a",{to:"#"},[(0,a._)("span",null,"Export to Drive")],-1);function Dn(t,e,o,n,r,i){const l=(0,a.up)("Content"),d=(0,a.up)("a-dropdown");return(0,a.wg)(),(0,a.j4)(d,{overlayClassName:t.className,placement:t.placement,title:t.title,trigger:t.action},{overlay:(0,a.w5)((()=>[(0,a.Wm)(l,null,{default:(0,a.w5)((()=>[(0,a.WI)(t.$slots,"overlay",{},(()=>[Sn,Fn,jn]))])),_:3})])),default:(0,a.w5)((()=>[(0,a.WI)(t.$slots,"default")])),_:3},8,["overlayClassName","placement","title","trigger"])}const Cn=d.ZP.div`
    background: #ffffff;
    box-shadow: 0px 0px 2px #888;
    a i, a svg, a img {
        ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 8px;
    }
    a {
        display: block;
        color: #888;
        padding: 6px 12px;
    }
    a:hover {
        background: ${({theme:t})=>t["primary-color"]}10;
        color: ${({theme:t})=>t["primary-color"]}
    }
    .dropdown-theme-2{
        a:hover{
            background: ${({theme:t})=>t.pink}10;
            color: ${({theme:t})=>t.pink}
        }
    }
`;var En=(0,a.aZ)({name:"Dropdown",components:{Content:Cn},props:{placement:Ge.ZP.string.def("bottomRight"),title:Ge.ZP.string,action:Ge.ZP.array.def(["hover"]),class:Ge.ZP.string.def("strikingDash-dropdown")},setup(t){return{className:(0,oo.iH)(t.class)}}});const Bn=(0,Q.Z)(En,[["render",Dn]]);var Ln=Bn;const Wn={to:"#"},An=(0,a._)("span",null,"Btn Dropdown one",-1),In={to:"#"},Nn=(0,a._)("span",null,"Btn Dropdown two",-1),Hn={to:"#"},On=(0,a._)("span",null,"Btn Dropdown three",-1);function Tn(t,e,o,n,r,i){const l=(0,a.up)("Title"),d=(0,a.up)("sdFeatherIcons"),p=(0,a.up)("Content"),s=(0,a.up)("a-popover");return(0,a.wg)(),(0,a.j4)(s,{class:"className",modelValue:t.visible,"onUpdate:modelValue":e[0]||(e[0]=e=>t.visible=e),placement:t.placement,title:t.title&&t.title,trigger:t.action},(0,a.Nv)({content:(0,a.w5)((()=>[(0,a.Wm)(p,null,{default:(0,a.w5)((()=>[(0,a.WI)(t.$slots,"content",{},(()=>[(0,a._)("a",Wn,[(0,a.Wm)(d,{type:"check",size:"16"}),An]),(0,a._)("a",In,[(0,a.Wm)(d,{type:"check",size:"16"}),Nn]),(0,a._)("a",Hn,[(0,a.Wm)(d,{type:"check",size:"16"}),On])]))])),_:3})])),default:(0,a.w5)((()=>[(0,a.WI)(t.$slots,"default")])),_:2},[t.title?{name:"title",fn:(0,a.w5)((()=>[(0,a.Wm)(l,null,{default:(0,a.w5)((()=>[(0,a.Uk)((0,Me.zw)(t.title),1)])),_:1})])),key:"0"}:void 0]),1032,["modelValue","placement","title","trigger"])}const Mn=d.ZP.div`  
  a, .span {
      display: block;
      color: #888;
      padding: 6px 12px;
      text-align: ${({theme:t})=>t.rtl?"right":"left"};
      span {
        ${({theme:t})=>t.rtl?"padding-right":"padding-left"}: 12px;
      }
  }
  a:hover {
    background: ${({theme:t})=>t["primary-color"]}10;
    color: ${({theme:t})=>t["primary-color"]}
  }
  
`,Un=d.ZP.p`
  text-align: ${({theme:t})=>t.rtl?"right":"left"};
  margin: 0;
`;var qn=(0,a.aZ)({name:"Popover",props:{placement:Ge.ZP.string.def("bottom"),title:Ge.ZP.string,class:Ge.ZP.string,action:Ge.ZP.string.def("hover")},setup(t){const e=!1,o=(0,oo.iH)(t.class);return{visible:e,className:o}},components:{Content:Mn,Title:Un}});const Rn=(0,Q.Z)(qn,[["render",Tn]]);var Vn=Rn;[kn,Je,$o,Po,Co,Vo,pn,{name:"PageHeader",...cn},zn,Ln,Vn,ro].map((t=>{nt.component(`sd${t.name}`,t)}));var Yn=o(29537);[{name:"UserCard",...Yn.Oe}].map((t=>{nt.component(`sd${t.name}`,t)})),nt.config.productionTip=!0,nt.use(Nt),nt.use(mt),nt.mount("#app")},29537:function(t,e,o){o.d(e,{Oe:function(){return a},Vx:function(){return r},or:function(){return i}});var n=o(23481);n.ZP.div`
  background: #fff;
  border-radius: 10px;
  box-shadow: 0px 5px 20px #9299B830;
  padding: 30px;
  .pricing-badge{
    height: 32px;
    padding: 6px 22.6px;
  }
  .price-amount{
    font-size: 36px;
    margin-bottom: 10px;
    .currency{
      font-size: 16px;
      font-weight: 600;
      top: -12px;
      ${({theme:t})=>t.rtl?"margin-right":"margin-left"}: 2px;
      color: ${({theme:t})=>t["extra-light-color"]};
    }
    .pricing-validity{
      font-size: 13px;
      font-weight: 400;
      bottom: 0;
      ${({theme:t})=>t.rtl?"right":"left"}: -2px;
      color: ${({theme:t})=>t["light-color"]};
    }
  }
  .package-user-type{
    font-size: 13px;
    font-weight: 500;
    color: ${({theme:t})=>t["gray-color"]};
  }
  .pricing-title{
    font-size: 24px;
    font-weight: 600;
    margin-bottom: 8px;
  }
  button{
    padding: 0px 29.32px;
    height: 44px;
    border-radius: 6px;
    &.ant-btn-white{
      border: 1px solid #E3E6EF;
      span{
        color: #272b41;
      }
    }
  }
`,n.ZP.div`
  margin: 28px 0 15px;
  min-height: 210px;
  .list-single{
    display: flex;
    align-items: center;
    &:not(:last-child){
      margin-bottom: 12px;
    }
    .icon{
      display: inline-block;
      margin: ${({theme:t})=>t.rtl?"0px 0 -4px 10px":"0px 10px -4px 0"};
    }
  }
`,(0,n.ZP)("span",["type"])`
  display: inline-block;
  margin-bottom: 32px;
  padding: 5px 20px;
  border-radius: 16px;
  background: ${({type:t,theme:e})=>e[`${t}-color`]}10;
  color: ${({type:t,theme:e})=>e[`${t}-color`]};
  font-size: 13px;
  font-weight: 500;
`,n.ZP.nav`
  background: #fff;
  margin-bottom: 25px;
  border-radius: 10px;
  padding: 0px 16px;
  @media only screen and (max-width: 767px){
    padding: 0 12px;
  }
  @media only screen and (max-width: 575px){
    text-align: center;
  }
  ul {
    margin: 0;
    padding: 0;
    list-style-type: none;
    li {
      display: inline-block;
      a {
        position: relative;
        display: block;
        padding: 15px 0;
        margin: 0 12px;
        color: ${({theme:t})=>t["light-color"]};
        @media only screen and (max-width: 767px){
          margin: 0 10px;
        }
        &:after{
          position: absolute;
          ${({theme:t})=>t.rtl?"right":"left"}: 0;
          bottom: 0;
          width: 100%;
          height: 2px;
          border-radius: 10px;
          opacity: 0;
          visibility: hidden;
          background: ${({theme:t})=>t["primary-color"]};
          content: "";
        }
        &.active{
          color: ${({theme:t})=>t["primary-color"]};
          &:after{
            opacity: 1;
            visibility: visible;
          }
        }
      }
    }
  }
`,n.ZP.nav`
  border-radius: 10px;
  background: #fff;
  box-shadow: 0 5px 20px ${({theme:t})=>t["light-color"]}03;
  figure{
    margin-bottom: 0;
  }
  .gallery-single-content{
    padding: 18px 25px 20px;
    .gallery-single-title{
      font-size: 15px;
      font-weight: 500;
      margin-bottom: 2px;
    }
    p{
      font-size: 13px;
      margin-bottom: 0px;
      color: ${({theme:t})=>t["light-color"]};
    }
  }
`;const a=n.ZP.div`
  text-align: center;
  .user-card{
    &.theme-list{
      .ant-card-body{
        padding: 30px 25px 30px 30px !important;
        @media only screen and (max-width: 479px){
          padding: 25px 20px 25px 20px !important;
        }
      }
      figure{
        display: flex;
        align-items: flex-start;
        justify-content: space-between;
        @media only screen and (max-width: 479px){
          flex-flow: column;
        }
        img{
          max-width: 80px;
          ${({theme:t})=>t.rtl?"margin-left":"margin-right"}:20px;
          @media only screen and (max-width: 479px){
            ${({theme:t})=>t.rtl?"margin-left":"margin-right"}:0px;
          }
        }
      }
      figcaption{
        display: flex;
        justify-content: space-between;
        align-items: flex-start;
        text-align: ${({theme:t})=>t.rtl?"right":"left"};
        @media only screen and (max-width: 379px){
          flex-flow: column;
        }
      }
      .card__content{
        p{
          max-width: 400px;
          font-size: 15px;
          color: ${({theme:t})=>t["gray-color"]};
        }
        .card__designation{
            font-size: 13px;
            margin-bottom: 15px;
            color: ${({theme:t})=>t["light-color"]};
        }
        .card-text{
          margin-bottom: 12px;
        }
        .card-info{
          margin-bottom: 0;
          .user-meta{
            font-size: 14px;
            strong{
              font-weight: 600;
              color: ${({theme:t})=>t["dark-color"]};
            }
          }
          .user-meta + .user-meta{
            ${({theme:t})=>t.rtl?"margin-right":"margin-left"}: 20px;
          }
        }
      }
      .card__actions{
        text-align: ${({theme:t})=>t.rtl?"left":"right"}
        @media only screen and (max-width: 379px){
          margin-top: 15px;
        }
        button{
          padding: 0px 19.05px;
          min-width: 114px;
        }
      }
    }
    &.theme-grid-2{
      .ant-card-body{
        padding: 0 !important;
      }
      figure{
        position: relative;
      }
      .user-card__img{
        margin-bottom: 0;
        position: absolute;
        top: 80px;
        left: 50%;
        transform: translateX(-50%);
        width: 132px;
        height: 132px;
        border-radius: 50%;
        background-color: #fff;
        z-index: 22;
        img{
          position: relative;
          top: 6px;
        }
      }
      .user-card__bg{
        background-size: cover !important;
        background-position: center !important;
        border-radius: 10px 10px 0 0;
      }
      .card__bottom{
        position: relative;
        background-color: #fff;
        top: -26px;
        padding-top: 102px;
        border-radius: 30px 30px 10px 10px;
      }
    }
    &.theme-grid-3{
      .ant-card{
        text-align: left;
      }
      .ant-card-body{
        padding: 0 !important;
      }
      .card__top,
      .card__content,
      .card__info{
        padding: 0 30px;
      }
      .card__top{
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding-top: 30px;
        margin-bottom: 10px;
        .user-card__img{
          margin-right: 12px;
          ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 12px;
          img{
            max-width: 70px;
          }
        }
        .user-card__info{
          width: 100%;
          position: relative;
          .action-more{
            position: absolute;
            right: 0;
            ${({theme:t})=>t.rtl?"left":"right"}: 0;
            top: 0;
            color: #ADB4D2;
          }
          .card__designation{
            margin-bottom: 0;
          }
        }
      }
      .card__content{
        p{
          font-size: 15px;
          margin-bottom: 26px;
        }
        .image-group{
          margin: -3px;
        }
        img{
          max-width: 34px;
          margin: 3px;
        }
      }
      .card__info{
        padding-bottom: 30px;
        padding-top: 18px;
        .ant-progress-inner{
          position: relative !important;
        }
        p{
          font-size: 12px;
          color: ${({theme:t})=>t["light-color"]};
        }
        h2{
          font-size: 14px;
          font-weight: 500;
          margin-top: 4px;
          margin-bottom: 16px;
        }
        .info-line{
          display: flex;
          justify-content: space-between;
          .success{
            color: ${({theme:t})=>t["success-color"]};
          }
        }
        .completed-count{
          margin-top: 4px;
        }
        .project-progress{
          display: flex;
          justify-content: space-between;
          .progress-percentage{
            ${({theme:t})=>t.rtl?"margin-right":"margin-left"}: 20px;
            span{
              font-size: 12px;
              color: ${({theme:t})=>t["gray-color"]};
            }
          }
          .ant-progress{
            &> div{
              flex-direction: row;
            }
            .ant-progress-text{
              display: none;
            }
          }
        }
      }
    }
  }
  .card{
    position: relative;
    box-shadow: 0 5px 20px ${({theme:t})=>t["light-color"]}03;
    .ant-card-body{
      padding: 30px !important;
      div{
        position: static;
      }
    }
    figure{
      margin-bottom: 0;
      img{
        margin-bottom: 20px;
        width: 100%;
        border-radius: 50%;
        max-width: 150px;
      }
    }
    .card__more_actions{
      position: absolute;
      ${({theme:t})=>t.rtl?"left":"right"}: 24px;
      top: 20px;
      line-height: .5;
      padding: 5px 3px;
      color: ${({theme:t})=>t["extra-light-color"]};
      box-shadow: 0 10px 20px #9299B815;
      svg,
      img{
        width: 20px;
      }
    }
    .card__name{
      font-size: 16px;
      margin-bottom: 6px;
      font-weight: 500;
      a{
        color: ${({theme:t})=>t["dark-color"]};
        &:hover{
          color: ${({theme:t})=>t["primary-color"]};
        }
      }
    }
    .card__designation{
      font-size: 13px;
      margin-bottom: 25px;
      color: ${({theme:t})=>t["light-color"]};
    }
    .card__social{
      margin-top: 16px;
      a{
        width: 38px;
        height: 38px;
        border-radius: 50%;
        display: inline-flex;
        align-items: center;
        justify-content: center;
        box-shadow: 0 10px 20px ${({theme:t})=>t["light-color"]}15;
        background: #fff;
        &:not(:last-child){
          ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 10px;
        }
        &.facebook{
            span.fa, svg{
            color: #3B5998;
          }
        }
        &.twitter {
            span.fa, svg{
            color: #1DA1F2;
          }
        }
        &.dribble {
            span.fa, svg{
            color: #C2185B;
          }
        }
        &.instagram {
            span.fa, svg{
            color: #FF0300;
          }
        }
      }
    }
  }

  .user-card{
    .ant-card-body{
      padding: 30px 25px 18px 25px !important;
      @media only screen and (max-width: 1599px){
        padding: 20px  20px 20px !important;
      }
      @media only screen and (max-width: 767px){
        padding: 15px  15px 15px !important;
      }
    }
    figure{
      img{
        margin-bottom: 18px;
        max-width: 150px;
      }
    }
    .card__actions{
      margin: -5px;
      .ant-btn-white{
        color: ${({theme:t})=>t["gray-color"]};
        border: 1px solid ${({theme:t})=>t["border-color-light"]};
        &:hover{
          border: 1px solid ${({theme:t})=>t["primary-color"]};
        }
      }
      button{
        font-size: 13px;
        padding: 0px 22.7px;
        height: 38px;
        border-radius: 6px;
        box-shadow: 0px 3px 5px ${({theme:t})=>t["light-color"]}05;
        margin: 5px;
        &:hover{
          color: #fff !important;
          background-color: ${({theme:t})=>t["primary-color"]} !important;
          svg,
          i{
            color: #fff;
          }
        }
        svg,
        i{
          color: ${({theme:t})=>t["light-color"]};
        }
      }
    }
    .card__info{
      padding-top: 20px;
      margin-top: 18px;
      border-top: 1px solid ${({theme:t})=>t["border-color-light"]};
      .info-single{
        text-align: center;
      }
      .info-single__title{
        font-size: 16px;
        font-weight: 600;
        line-height: 1.5;
        margin-bottom: 4px;
      }
      p{
        margin-bottom: 0;
        color: ${({theme:t})=>t["light-color"]};
      }
    }
  }
`,r=(n.ZP.div`
  .faq-badge{
    font-weight: 400;
    color: ${({theme:t})=>t["light-color"]};
    background: ${({theme:t})=>t["bg-color-light"]};
  }
  ul{
    li{
      a{
        display: inline-block;
        font-weight: 500;
        position: relative;
        padding: ${({theme:t})=>t.rtl?"12px 20px 12px 0":"12px 0 12px 20px"};
        transition: all .3s ease;
        color: ${({theme:t})=>t["gray-color"]};
        background-color: #fff;
        &.active{
          padding-left: 28px;
          &:before{
            opacity: 1;
            visibility: visible;
            ${({theme:t})=>t.rtl?"right":"left"}: -15px;
          }
          &:after{
            ${({theme:t})=>t.rtl?"right":"left"}: 5px;
          }
          &.primary{
            &:after{
              background: ${({theme:t})=>t["primary-color"]};
            }
          }
          &.secondary{
            &:after{
              background: ${({theme:t})=>t["secondary-color"]};
            }
          }
          &.success{
            &:after{
              background: ${({theme:t})=>t["success-color"]};
            }
          }
          &.warning{
            &:after{
              background: ${({theme:t})=>t["warning-color"]};
            }
          }
          &.info{
            &:after{
              background: ${({theme:t})=>t["info-color"]};
            }
          }
          &.danger{
            &:after{
              background: ${({theme:t})=>t["danger-color"]}5;
            }
          }
        }
        &:before{
          position: absolute;
          ${({theme:t})=>t.rtl?"right":"left"}: -25px;
          top: 0;
          height: 100%;
          width: 2px;
          border-radius: 10px;
          opacity: 0;
          visibility: hidden;
          content: '';
          background: ${({theme:t})=>t["primary-color"]};
          transition: all .3s ease;
        }
        &:after{
          position: absolute;
          ${({theme:t})=>t.rtl?"right":"left"}: 0;
          top: 50%;
          transform: translatey(-50%);
          width: 8px;
          height: 8px;
          border-radius: 50%;
          content: '';
          background: ${({theme:t})=>t["primary-color"]}50;
          transition: all .3s ease;
        }
        &.secondary{
          &:after{
            background: ${({theme:t})=>t["secondary-color"]}50;
          }
          &:before{
            background: ${({theme:t})=>t["secondary-color"]};
          }
        }
        &.success{
          &:after{
            background: ${({theme:t})=>t["success-color"]}50;
          }
          &:before{
            background: ${({theme:t})=>t["success-color"]};
          }
        }
        &.warning{
          &:after{
            background: ${({theme:t})=>t["warning-color"]}50;
          }
          &:before{
            background: ${({theme:t})=>t["warning-color"]};
          }
        }
        &.info{
          &:after{
            background: ${({theme:t})=>t["info-color"]}50;
          }
          &:before{
            background: ${({theme:t})=>t["info-color"]};
          }
        }
        &.danger{
          &:after{
            background: ${({theme:t})=>t["danger-color"]}50;
          }
          &:before{
            background: ${({theme:t})=>t["danger-color"]};
          }
        }
      }
    }
  }
`,n.ZP.div`
  text-align: center;
  .ant-card-body{
    padding: 30px 50px 40px 50px !important;
    @media only screen and (max-width: 1599px){
      padding: 30px !important;
    }
    @media only screen and (max-width: 991px){
      padding: 25px !important;
    }
  }
  figure{
    margin-bottom: 30px;
    img{
      width: 100%;
    }
  }
  h1,
  h2,
  h3,
  h4,
  h5,
  h6{
    font-size: 18px;
    font-weight: 500;
    margin-bottom: 20px;
    color: ${({theme:t})=>t["dark-color"]};
  }
  button{
    padding: 0 30px;
    border-radius: 6px;
    height: 44px;
  }
`,n.ZP.div`
  .ant-card{
    .ant-card-body{
      h1{
        font-weight: 500;
      }
    }
  }
  .ant-collapse{
    margin-top: 25px;
    &.ant-collapse-borderless{
      background: #fff;
    }
    &.ant-collapse-icon-position-left{
      .ant-collapse-header{
        color: ${({theme:t})=>t["dark-color"]} !important;
      }
    }
  }
  .ant-collapse-item{
    border: 1px solid ${({theme:t})=>t["border-color-light"]} !important;
    margin-bottom: 5px;
    &.ant-collapse-item-active{
      box-shadow: 0px 15px 40px ${({theme:t})=>t["light-color"]}15;
    }
    .ant-collapse-header{
      font-weight: 500;
      font-size: 15px;
      background-color: #fff;
      padding: 18px 25px !important;
      border-radius: 5px !important;
      @media only screen and (max-width: 575px){
        padding: ${({theme:t})=>t.rtl?"15px 15px 15px 45px":"15px 45px 15px 15px"} !important;
      }
      .ant-collapse-arrow{
        ${({theme:t})=>t.rtl?"right":"left"}: auto !important;
        ${({theme:t})=>t.rtl?"left":"right"}: 25px !important;
        top: 22px !important;
        transform: translateY(0) !important;
      }
    }
  }

  .ant-collapse-content{
    background-color: #fff !important;
    box-shadow: 0 15px 40px ${({theme:t})=>t["light-color"]}15;
    .ant-collapse-content-box{
      border-top: 1px solid ${({theme:t})=>t["border-color-light"]} !important;
      padding: 20px 25px 30px !important;
      P{
        font-size: 15px;
        margin-bottom: 35px;
        line-height: 1.667;
        color: ${({theme:t})=>t["gray-color"]};
      }
      h1,
      h2,
      h3,
      h4,
      h5,
      h6{
        font-size: 14px;
        font-weight: 500;
        margin-bottom: 12px;
        color: ${({theme:t})=>t["dark-color"]};
      }
      .panel-actions{
        display: flex;
        button{
          height: 36px;
          padding: 0 15px;
          &:not(:last-child){
            ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 10px;
          }
        }
      }
    }
  }
`,n.ZP.div`
  .ant-select{
    @media only screen and (max-width: 575px){
      width: 100% !important;
    }
  }
  .ant-select-selector{
    height: 48px !important;
    .ant-select-selection-search{
      height: 48px;
      width: 100% !important;
      input{
        height: 46px !important;
      }
    }
    .ant-input-affix-wrapper{
      border: 0 none;
      padding-top: 0 !important;
      padding-bottom: 0 !important;
    }
    .ant-select-selection-search-input {
      border-radius: 100px;
    }
  }
  .ant-input-suffix{
    i{
      font-size: 14px;
      top: 0 !important;
    }
    svg{
      width: 14px;
      height: 14px;
    }
  }
  .search-filter-menu{
    margin: 22px 0 20px;
    @media only screen and (max-width: 575px){
      text-align: center;
    }
    ul{
      li{
        display: inline-block;
        margin-bottom: 10px;
        &:not(:last-child){
          ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 10px;
        }
        a{
          font-size: 13px;
          font-weight: 500;
          display: block;
          padding: 5px 15px;
          border-radius: 5px;
          color: ${({theme:t})=>t["light-color"]};
          box-shadow: 0 3px 6px ${({theme:t})=>t["light-color"]}05;
          background: #fff;
          &.active{
            color: #fff;
            background: ${({theme:t})=>t["primary-color"]};
          }
        }
      }
    }
  }
`,n.ZP.div`
  .result-list-top{
    max-width: 1000px;
    border-bottom: 1px solid ${({theme:t})=>t["border-color-light"]};
    margin-bottom: 20px;
    padding-bottom: 24px;
  }
  .search-found-text{
    font-size: 16px;
    margin-bottom: 0;
    color: ${({theme:t})=>t["light-color"]};
    .result-count{
      ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 5px;
    }
    .result-keyword{
      ${({theme:t})=>t.rtl?"margin-right":"margin-left"}: 4px;
    }
    .result-count,
    .result-keyword{
      font-weight: 600;
      color: ${({theme:t})=>t["dark-color"]};
    }
  }
  .result-limit{
    text-align: ${({theme:t})=>t.rtl?"left":"right"};
    margin-bottom: 0;
    color: ${({theme:t})=>t["light-color"]};
    @media only screen and (max-width: 767px){
      text-align: ${({theme:t})=>t.rtl?"right":"left"};
      margin-top: 10px;
    }
  }
  .result-list-content{
    border-bottom: 1px solid ${({theme:t})=>t["border-color-light"]};
    padding-bottom: 14px;
    margin-bottom: 30px;
    ul{
      li{
        &:not(:last-child){
          margin-bottom: 35px;
        }
        .result-list-title{
          font-size: 16px;
          font-weight: 500;
          margin-bottom: 10px;
          .search-keyword{
            font-weight: 600;
            color: ${({theme:t})=>t["primary-color"]};
          }
        }
        p{
          color: ${({theme:t})=>t["gray-color"]};
        }
      }
    }
  }
  .ant-pagination{
    @media only screen and (max-width: 575px){
      text-align: center;
    }
  }
`,n.ZP.div`
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  width: 100%;
  min-height: 100vh;
  text-align: center;
  img{
    margin-bottom: 72px;
    max-width: 400px;
    width: 100%;
    @media only screen and (max-width: 575px){
      margin-bottom: 30px;
    }
  }
  h1,
  h2,
  h3,
  h4,
  h5,
  h6{
    font-size: 18px;
    font-weight: 500;
    margin-bottom: 15px;
  }
  p{
    color: ${({theme:t})=>t["gray-color"]};
  }
`,n.ZP.div`
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  width: 100%;
  min-height: 100vh;
  text-align: center;
  img{
    margin-bottom: 100px;
    max-width: 400px;
    width: 100%;
    @media only screen and (max-width: 575px){
      margin-bottom: 30px;
    }
  }
  .error-text{
    font-size: 60px;
    font-weight: 600;
    margin-bottom: 35px;
    color: ${({theme:t})=>t["extra-light-color"]};
  }
  p{
    font-size: 18px;
    font-weight: 500;
    margin-bottom: 26px;
  }
  button{
    height: 44px;
  }
`),i=(n.ZP.div`
  text-align: center;
  background: #fff;
  padding: 75px 0 30px;
  margin-bottom: 30px;
  @media only screen and (max-width: 1150px){
    padding: 50px 0 6px;
  }
  @media only screen and (max-width: 991px){
    padding: 20px 0 0px;
  }
  .strikingDash-logo{
    margin-bottom: 55px;
    @media only screen and (max-width: 1150px){
      margin-bottom: 30px;
    }
    @media only screen and (max-width: 767px){
      margin-bottom: 25px;
    }
    img{
      max-width: 170px;
    }
  }
  .coming-soon-content{
    h1{
      font-size: 58px;
      font-weight: 600;
      line-height: 1.5;
      margin-bottom: 25px;
      color: ${({theme:t})=>t["dark-color"]};
      @media only screen and (max-width: 991px){
        font-size: 48px;
        margin-bottom: 15px;
      }
      @media only screen and (max-width: 767px){
        font-size: 40px;
        line-height: 1.45;
      }
      @media only screen and (max-width: 479px){
        font-size: 30px;
      }
      @media only screen and (max-width: 375px){
        font-size: 20px;
      }
    }
    p{
      font-size: 17px;
      max-width: 580px;
      margin: 0 auto 25px;
      color: ${({theme:t})=>t["gray-color"]};
      @media only screen and (max-width: 991px){
        margin-bottom: 15px;
      }
      @media only screen and (max-width: 767px){
        font-size: 16px;
      }
      @media only screen and (max-width: 375px){
        font-size: 15px;
      }
    }
  }
  .countdwon-data{
    display: flex;
    justify-content: center;
    >span{
      &:not(:last-child){
        margin-right: 50px;
        ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 50px;
        @media only screen and (max-width: 575px){
          margin-right: 20px;
          ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 20px;
        }
      }
    }
  }
  .strikingDash-countdown{
    .countdown-time{
      font-size: 42px;
      font-weight: 600;
      line-height: 1.45;
      color: ${({theme:t})=>t["dark-color"]};
      @media only screen and (max-width: 991px){
        font-size: 32px;
      }
      @media only screen and (max-width: 575px){
        font-size: 26px;
      }
      @media only screen and (max-width: 375px){
        font-size: 20px;
      }
    }
    .countdown-title{
      font-size: 16px;
      font-weight: 400;
      display: block;
      color: ${({theme:t})=>t["gray-color"]};
      @media only screen and (max-width: 375px){
        font-size: 15px;
      }
    }
  }
  .subscription-form{
    margin-top: 40px;
    @media only screen and (max-width: 991px){
      margin-top: 25px;
    }
    @media only screen and (max-width: 1150px){
      margin-top: 35px;
    }
    .subscription-form-inner{
      display: flex;
      justify-content: center;
      @media only screen and (max-width: 375px){
        flex-flow: column;
        margin-bottom: 20px;
      }
      .ant-form-item-control{
        margin-right: 20px;
        ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 20px;
        @media only screen and (max-width: 375px){
          margin-right: 0;
          ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 0;
        }
        .ant-input{
          min-width: 320px;
          padding: 12px 20px;
          @media only screen and (max-width: 767px){
            min-width: 100%;
          }
          &::placeholder{
            color: ${({theme:t})=>t["extra-light-color"]};
          }
        }
      }
      button{
        font-size: 14px;
        text-transform: uppercase;
        font-weight: 500;
      }
    }
  }
  .coming-soon-social{
    margin-top: 50px;
    @media only screen and (max-width: 1150px){
      margin-top: 25px;
    }
    @media only screen and (max-width: 767px){
      margin-top: 30px;
    }
    ul{
      margin-bottom: 30px;
      li{
        display: inline-block;
        &:not(:last-child){
          margin-right: 15px;
          ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 15px;
        }
        a{
          display: flex;
          align-items: center;
          justify-content: center;
          width: 44px;
          height: 44px;
          border-radius: 50%;
          i,
          span,
          svg{
            color: #fff;
          }
          &.facebook{
            background-color: #3B5998;
          }
          &.twitter{
            background-color: #1DA1F2;
          }
          &.globe{
            background-color: #DD3E7C;
          }
          &.github{
            background-color: #23282D;
          }
        }
      }
    }
    p{
      font-size: 14px;
      color: ${({theme:t})=>t["light-color"]};
    }
  }
`,n.ZP.div`
  .form-title{
    font-size: 18px;
    font-weight: 500;
    margin-bottom: 36px;
  }
  .add-user-wrap{
   $:
  }
  .add-user-bottom{
    margin-top: 20px;
    button + button{
      ${({theme:t})=>t.rtl?"margin-right":"margin-left"}: 15px;
    }
    .ant-btn-light{
      background: ${({theme:t})=>t["bg-color-light"]};
      border: 1px solid #F1F2F6;
    }
    &.text-right{
      @media only screen and (max-width: 767px){
        text-align: ${({theme:t})=>t.rtl?"right":"left"} !important;
      }
    }
  }
  .card-nav{
    ul{
      flex-wrap: wrap;
      margin-bottom: -4px -10px;
      @media only screen and (max-width: 575px){
        justify-content: center;
      }
      li{
        margin: 4px 10px !important;
        &:not(:last-child){
          ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 26px;
          @media only screen and (max-width: 575px){
            ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 0;
          }
        }
        a{
          position: relative;
          padding: 22px 0;
          font-size: 14px;
          font-weight: 500;
          color: ${({theme:t})=>t["gray-color"]};
          @media only screen and (max-width: 575px){
            padding: 0;
          }
          &:after{
            position: absolute;
            ${({theme:t})=>t.rtl?"right":"left"}: 0;
            bottom: -4px;
            width: 100%;
            height: 2px;
            border-radius: 4px;
            content: '';
            opacity: 0;
            visibility: hidden;
            background-color: ${({theme:t})=>t["primary-color"]};
            @media only screen and (max-width: 575px){
              display: none;
            }
          }
          &.active{
            color: ${({theme:t})=>t["primary-color"]};
            &:after{
              opacity: 1;
              visibility: visible;
            }
            svg,
            img,
            i,
            span{
              color: ${({theme:t})=>t["primary-color"]};
            }
          }
          svg,
          img,
          i,
          span{
            color: ${({theme:t})=>t["light-color"]};
            ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 10px;
          }
        }
      }
    }
  }

  /* // Photo Upload */
  .photo-upload{
    position: relative;
    max-width: 260px;
    margin-bottom: 30px;
    .ant-upload-select{
      display: inline-flex;
      align-items: center;
      justify-content: center;
      height: 40px;
      width: 40px;
      border-radius: 50%;
      position: absolute;
      ${({theme:t})=>t.rtl?"right":"left"}: 85px;
      bottom: 5px;
      z-index: 10;
      background-color: ${({theme:t})=>t["white-color"]};
      span{
        display: inline-flex;
        align-items: center;
        justify-content: center;
        width: 32px;
        height: 32px;
        border-radius: 50%;
        z-index: -1;
        background-color: ${({theme:t})=>t["primary-color"]};
      }
      svg,
      i,
      span{
        color: ${({theme:t})=>t["white-color"]};
      }
      a{
        display: flex;
        align-items: center;
        justify-content: center;
      }
    }
    img{
      border-radius: 50%;
    }
    .info{
      background-color: transparent;
    }
    figcaption{
      ${({theme:t})=>t.rtl?"margin-right":"margin-left"}: 20px;
      .info{
        h1,
        h2,
        h3,
        h4,
        h5,
        h6{
          font-size: 15px;
          font-weight: 500;
        }
      }
    }
  }

  .user-work-form{
    .ant-picker{
      padding: 0 15px 0 0;
    }
  }
  .user-info-form{
    .ant-select-single .ant-select-selector .ant-select-selection-item{
      color: ${({theme:t})=>t["gray-color"]};
    }
  }
  .social-form{
    .ant-form-item-control-input-content{
      .ant-input-prefix{
        width: 44px;
        height: 44px;
        border-radius: 4px;
      }
    }
    .ant-form-item-control-input{
      height: 44px;
      .ant-input-affix-wrapper{
        &:hover,
        &:focus,
        &.ant-input-affix-wrapper-focused{
          border-color: #E3E6EF;
        }
        .ant-input{
          height: 42px;
          ${({theme:t})=>t.rtl?"padding-right":"padding-left"}: 0;
        }
      }
    }
    .ant-input-prefix{
      position: relative;
      ${({theme:t})=>t.rtl?"right":"left"}: -11px;
      span{
        display: flex;
        align-items: center;
        justify-content: center;
        border-radius: 4px;
        width: 100%;
        height: 100%;
        background-color: ${({theme:t})=>t["primary-color"]};
        i,
        svg,
        span.fa{
          color: #fff;
          font-size: 16px;
        }
        .fa-facebook{
          background-color: #3B5998;
        }
        .fa-twitter{
          background-color: #38B8FF;
        }
        .fa-linkedin{
          background-color: #2CAAE1;
        }
        .fa-instagram{
          background-color: #FF0300;
        }
        .fa-github{
          background-color: #292929;
        }
        .fa-youtube{
          background-color: #FE0909;
        }
      }
    }
  }
`,n.ZP.div`
   .ant-card-head{
     .ant-card-head-title{
       .v-num{
        $: 0;
         font-size: 18px;
         color: ${({theme:t})=>t["dark-color"]};
       }
       .sign{
         font-size: 18px;
         color: ${({theme:t})=>t["dark-color"]};
         display: inline-block;
         margin: 0 8px;
       }
       .rl-date{
        $: 0;
         font-weight: 400;
         font-size: 16px;
       }
     }
   }
  .version-list{
    .version-list__single{
      &:not(:last-child){
        margin-bottom: 30px;
      }
      ul{
        li{
          position: relative;
          ${({theme:t})=>t.rtl?"padding-right":"padding-left"}: 20px;
          font-size: 16px;
          color: ${({theme:t})=>t["gray-color"]};
          &:not(:last-child){
            margin-bottom: 12px;
          }
          &:after{
            position: absolute;
            ${({theme:t})=>t.rtl?"right":"left"}: 0;
            top: 50%;
            transform: translateY(-50%);
            width: 6px;
            height: 6px;
            border-radius: 50%;
            content: "";
          }
        }
        &.version-primary{
          li{
            &:after{
              background-color: ${({theme:t})=>t["primary-color"]};
            }
          }
        }
        &.version-success{
          li{
            &:after{
              background-color: ${({theme:t})=>t["success-color"]};
            }
          }
        }
        &.version-info{
          li{
            &:after{
              background-color: ${({theme:t})=>t["info-color"]};
            }
          }
        }
      }
    }
    .version-list__top{
      .badge{
        font-size: 12px;
        line-height: 1.2;
        letter-spacing: 1.4px;
        font-weight: 500;
        display: inline-block;
        padding: 5px 8px;
        height: auto;
        border-radius: 4px;
        margin-bottom: 14px;
        color: #fff;
        &.badge-primary{
          background-color: ${({theme:t})=>t["primary-color"]};
        }
        &.badge-info{
          background-color: ${({theme:t})=>t["info-color"]};
        }
        &.badge-success{
          background-color: ${({theme:t})=>t["success-color"]};
        }
      }
    }
  }

  .changelog-accordion{
    margin-top: 30px;
    .ant-collapse{
      background-color: transparent;
      border: 0 none;
    }
    .ant-collapse-item{
      border-radius: 6px;
      border: 1px solid ${({theme:t})=>t["border-color-normal"]};
      &:not(:last-child){
        margin-bottom: 20px;
      }
      &:last-child{
        border-radius: 6px;
        .ant-collapse-header{
          border-radius: 6px;
        }
      }
    }
    .ant-collapse-header{
      border-radius: 6px;
      padding: 20px 30px 18px 30px !important;
      @media only screen and (max-width: 575px){
        padding: 16px 20px 14px 20px !important;
      }
      .ant-collapse-arrow{
        left: auto !important;
        right: 30px;
        svg,
        img{
          width: 14px;
        }
      }
      .v-num{
        font-size: 18px;
        font-weight: 500;
        color: ${({theme:t})=>t["dark-color"]};
        @media only screen and (max-width: 575px){
          font-size: 16px;
        }
      }
      .rl-date{
        font-size: 16px;
        font-weight: 400;
        @media only screen and (max-width: 575px){
          font-size: 14px;
        }
      }
    }
    .ant-collapse-content{
      border-radius: 0 0 6px 6px;
      > .ant-collapse-content-box{
        padding: 30px 30px 25px;
      }
    }
  }
`,n.ZP.div`
  .history-title{
    font-size: 11px;
    margin-bottom: 24px;
    color: ${({theme:t})=>t["light-gray-color"]};
  }
  .v-history-list{
    li{
      display: flex;
      justify-content: space-between;
      &:not(:last-child){
        margin-bottom: 24px;
      }
      .version-name{
        font-size: 14px;
        font-weight: 500;
        color: ${({theme:t})=>t["dark-color"]};
      }
      .version-date{
        font-size: 14px;
        color: ${({theme:t})=>t["light-gray-color"]};
      }
    }
  }
`,n.ZP.div`
  position: relative;
  padding: 0 150px;
  .button-group {
      width: 100%;
      display: flex;
      justify-content: space-between;
      position: absolute;
      top: 50%;
      left: 0;
  }
`,n.ZP.div`
  .ant-card{
    direction: ltr;
  }
  .ant-card-body{
    padding: 0 !important;
  }
  .testimonial-block{
    .swiper-button-prev,
    .swiper-button-next{
      width: 44px;
      height: 44px;
      border-radius: 50%;
      box-shadow: 0 3px 10px ${({theme:t})=>t["dark-color"]}16;
      @media only screen and (max-width: 991px){
        width: 35px;
        height: 35px;
      }
      &:after{
        line-height: 0;
      }
    }
    .testimonial-title{
      font-size: 30px;
      font-weight: 600;
      margin-bottom: 50px;
      color: ${({theme:t})=>t["dark-color"]};
      @media only screen and (max-width: 991px){
        font-size: 24px;
      }
    }
    &.theme-1{
      padding: 60px 100px 75px 100px;
      @media only screen and (max-width: 1599px){
        padding: 60px 50px 75px 50px;
      }
      @media only screen and (max-width: 1399px){
        padding: 50px 25px 45px 25px;
      }
      .testimonial-title{
        margin-bottom: 0;
      }
      .swiper-button-prev,
      .swiper-button-next{
        cursor: pointer;
        display: flex;
        align-items: center;
        justify-content: center;
        position: absolute;
        top: 50%;
        transform: translateY(-80%);
        z-index: 22;
        background-color: #fff;
      }
      .swiper-button-prev{
        left: 10px;
        &:before{
          font-family: 'Inter';
          content: url('${o(47156)}');
        }
      }
      .swiper-button-next{
        right: 10px;
        &:before{
          font-family: 'Inter';
          content: url('${o(84908)}');
        }
      }
      .swiper-pagination{
        .swiper-pagination-bullet{
          width: 10px;
          height: 10px;
          background-color: #DCDDFA;
          opacity: 1;
          &.swiper-pagination-bullet-active{
            background-color: ${({theme:t})=>t["primary-color"]};
          }
        }
      }
      .swiper-container{
        padding: 40px 25px 60px;
      }
      .swiper-slide {
        text-align: center;
        background-color: #fff;
        border-radius: 8px;
        padding: 40px;
        box-shadow: 0 10px 20px ${({theme:t})=>t["light-color"]}10;
        @media only screen and (max-width: 1399px){
          padding: 25px;
        }
        @media only screen and (max-width: 479px){
          padding: 25px 15px;
        }
        &.swiper-slide-active{
          box-shadow: 0 20px 50px ${({theme:t})=>t["light-color"]}20;
        }
      }
      .testimonial-block__single{
          figure{
            margin-bottom: 0;
            img{
              margin-bottom: 12px;
            }
          }
          .client-name{
            font-size: 15px;
            margin-bottom: 5px;
            color: ${({theme:t})=>t["dark-color"]}
          }
          .client-designation{
            font-size: 13px;
            font-weight: 400;
            opacity: .70;
            margin-bottom: 26px;
            color: ${({theme:t})=>t["gray-color"]};
          }
          .client-review{
            font-size: 16px;
            margin-bottom: 0;
            line-height: 1.75;
            color: ${({theme:t})=>t["gray-color"]};
          }
      }
    }
    &.theme-2{
      padding: 60px 100px 75px 100px;
      background-color: ${({theme:t})=>t["bg-color-light"]};
      @media only screen and (max-width: 1399px){
        padding: 60px 40px 75px 40px;
      }
      @media only screen and (max-width: 991px){
        padding: 50px 70px 55px 70px;
      }
      @media only screen and (max-width: 575px){
        padding: 30px 30px 45px 30px;
      }
      .testimonial-title{
        @media only screen and (max-width: 991px){
          margin-bottom: 20px;
        }
      }
      .swiper-button-prev,
      .swiper-button-next{
        cursor: pointer;
        display: flex;
        align-items: center;
        justify-content: center;
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        z-index: 22;
        background-color: #fff;
        @media only screen and (max-width: 991px){
          top: auto;
          bottom: -6px;
        }
      }
      .swiper-button-prev{
        left: 5px;
        @media only screen and (max-width: 991px){
          left: 42%;
        }
        @media only screen and (max-width: 575px){
          left: 36%;
        }
        &:before{
          font-family: 'Inter';
          content: url('${o(47156)}');
        }
      }
      .swiper-button-next{
        right: 5px;
        @media only screen and (max-width: 991px){
          right: 42%;
        }
        @media only screen and (max-width: 575px){
          right: 36%;
        }
        &:before{
          font-family: 'Inter';
          content: url('${o(84908)}');
        }
      }
      .swiper-container{
        padding: 0 20px;
        @media only screen and (max-width: 991px){
          padding: 30px 0 70px 0;
        }
      }
      .swiper-slide {
        background-color: #fff;
        border-radius: 8px;
        padding: 40px;
        box-shadow: 0 10px 30px ${({theme:t})=>t["light-color"]}10;
        @media only screen and (max-width: 479px){
          padding: 24px;
        }
      }
      .testimonial-block__single{
        position: relative;
        .quotation{
          position: absolute;
          ${({theme:t})=>t.rtl?"left":"right"}: 40px;
          top: 40px;
          @media only screen and (max-width: 479px){
            ${({theme:t})=>t.rtl?"left":"right"}: 25px;
          }
          img{
            @media only screen and (max-width: 479px){
              max-width: 40px;
            }
          }
        }
      }
      .testimonial-block__author{
        direction: ${({theme:t})=>t.rtl?"rtl":"ltr"};
        display: flex;
        align-items: center;
        margin-bottom: 22px;
        img{
          max-width: 70px;
          margin-right: 18px;
          ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 18px;
          @media only screen and (max-width: 479px){
            max-width: 60px;
            ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 10px;
          }
        }
        .author-info{
          .client-name{
            font-size: 15px;
            font-weight: 500;
            margin-bottom: 5px;
          }
          .client-designation{
            font-size: 13px;
            opacity: .70;
            margin-bottom: 0;
          }
          .info{
            background-color: #fff;
          }
        }
      }
      .testimonial-block__review{
        direction: ${({theme:t})=>t.rtl?"rtl":"ltr"};
        p{
          margin-bottom: 0;
          line-height: 1.75;
          font-size: 16px;
          color: ${({theme:t})=>t["gray-color"]};
        }
      }
    }
    &.theme-3{
      padding-top: 95px;
      @media only screen and (max-width: 991px){
        padding-top: 42px;
      }
      .testimonial-title{
        margin-bottom: 0;
      }
      .swiper-pagination {
        position: absolute;
        height: fit-content;
        top: 45px;
        left: 50%;
        transform: translateX(-50%);
        @media only screen and (max-width: 575px){
          top: 20px;
        }
        .pagination-thumb{
          display: inline-block;
          width: auto;
          height: auto;
          opacity: .4;
          margin: 4px 0;
          img{
            max-width: 70px;
            @media only screen and (max-width: 991px){
              max-width: 40px;
            }
            @media only screen and (max-width: 479px){
              max-width: 25px;
            }
          }
          &.swiper-pagination-bullet-active{
            position: relative;
            z-index: 22;
            opacity: 1;
            img{
              transform: scale(1.4);
            }
          }
        }
      }
      .testimonial-block__single{
        padding: 175px 0 86px;
        @media only screen and (max-width: 991px){
          padding: 135px 30px 36px;
        }
        @media only screen and (max-width: 575px){
          padding: 80px 30px 40px;
        }
      }
      .testimonial-block__inner{
        max-width: 800px;
        margin: 0 auto;
        text-align: center;
      }
      .testimonial-block__review{
        p{
          font-size: 16px;
          color: ${({theme:t})=>t["gray-color"]};
        }
      }
      .testimonial-block__author{
        .author-info{
          margin-top: 8px;
        }
        .author-name{
          font-size: 16px;
          font-weight: 500;
          margin-bottom: 3px;
        }
        .author-designation{
          opacity: .70;
          font-size: 14px;
        }
      }
    }
    &.theme-4{
      padding: 60px 100px 75px 100px;
      background-color: ${({theme:t})=>t["bg-color-light"]};
      @media only screen and (max-width: 1599px){
        padding: 60px 60px 75px 60px;
      }
      @media only screen and (max-width: 991px){
        padding: 50px 30px 48px 30px;
      }
      .swiper-button-prev,
      .swiper-button-next{
        cursor: pointer;
        display: flex;
        align-items: center;
        justify-content: center;
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        z-index: 22;
        background-color: #fff;
        @media only screen and (max-width: 575px){
          top: auto;
          bottom: -6px;
        }
      }
      .swiper-button-prev{
        left: 100px;
        @media only screen and (max-width: 1599px){
          left: 50px;
        }
        @media only screen and (max-width: 575px){
          left: 36%;
        }
        &:before{
          font-family: 'Inter';
          content: url('${o(47156)}');
        }
      }
      .swiper-button-next{
        right: 100px;
        @media only screen and (max-width: 1599px){
          right: 50px;
        }
        @media only screen and (max-width: 575px){
          right: 36%;
        }
        &:before{
          font-family: 'Inter';
          content: url('${o(84908)}');
        }
      }
      .swiper-container{
        @media only screen and (max-width: 575px){
          padding: 0 0 70px;
        }
      }
      .testimonial-block__inner{
        max-width: 1000px;
        margin: 0 auto;
        padding: 50px;
        background-color: #fff;
        box-shadow: 0 10px 30px ${({theme:t})=>t["light-color"]}10;
        text-align: center;
        @media only screen and (max-width: 1599px){
          max-width: 570px;
          padding: 30px;
        }
        @media only screen and (max-width: 991px){
          max-width: 450px;
        }
      }
      .testimonial-block__author{
        img{
          max-width: 100px;
          margin-bottom: 26px;
        }
      }
      .author-info{
        margin-top: 18px;
        .client-name{
          font-size: 15px;
          font-weight: 500;
          margin-bottom: 5px;
        }
        .client-designation{
          font-size: 13px;
          opacity: .70;
          margin-bottom: 0;
        }
      }
      .testimonial-block__review{
        p{
          margin-bottom: 0;
          line-height: 1.75;
          font-size: 16px;
          color: ${({theme:t})=>t["gray-color"]};
        }
      }
    }
  }
  .testimonial-title{
    text-align: center;
  }
`,n.ZP.div`
  background-color: ${({theme:t})=>t["bg-color-light"]};
  border-radius: 10px;
  box-shadow: 0 5px 20px #9299B810;
  margin-bottom: 50px;
  .sDash-support-container{
    max-width: 1110px;
    margin: 0 auto;
    @media only screen and (max-width: 1599px){
      max-width: 990px;
    }
    @media only screen and (max-width: 1399px){
      max-width: 790px;
    }
    @media only screen and (max-width: 1150px){
      max-width: 100%;
      padding: 30px
    }
  }
  .sDash_support-content{
    .sDash_support-content__title{
      font-size: 30px;
      font-weight: 600;
      margin-bottom: 10px;
      color: ${({theme:t})=>t["dark-color"]};
      @media only screen and (max-width: 767px){
        font-size: 26px;
      }
    }
    p{
      color: ${({theme:t})=>t["gray-color"]};
    }
    .btn-ticket{
      margin-top: 15px;
      height: 46px;
      font-size: 14px;
      font-weight: 500;
      @media only screen and (max-width: 575px){
        margin-top: 0;
      }
    }
  }
  .sDash_support-img{
    margin-top: 50px;
    @media only screen and (max-width: 1150px){
      margin-top: 0;
    }
    img{
      width: 100%;
      @media only screen and (max-width: 575px){
        margin-top: 30px;
      }
    }
  }
`,n.ZP.div`
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 5px 20px #9299B810;
  padding: 100px 0 50px 0;
  @media only screen and (max-width: 1599px){
    padding: 50px 0 20px 0;
  }
  @media only screen and (max-width: 1199px){
    margin-bottom: 30px;
  }
  .sDash-support-container{
    max-width: 1110px;
    margin: 0 auto;
    @media only screen and (max-width: 1599px){
      max-width: 990px;
    }
    @media only screen and (max-width: 1399px){
      max-width: 790px;
    }
    @media only screen and (max-width: 1150px){
      max-width: 100%;
      padding: 30px
    }
  }
  .sDash-support-link-item{
    max-width: 350px;
    margin: 0 auto 30px;
    padding: 30px;
    text-align: center;
    border-radius: 6px;
    transiiton: .35s;
    border: 1px solid ${({theme:t})=>t["border-color-light"]};
    &:hover{
      box-shadow: 0 15px 25px #9299BB15;
      border-color: #fff;
    }
    .sDash-support-link-item__icon{
      height: 80px;
      width: 80px;
      margin: 0 auto 28px;
      display: flex;
      align-items: center;
      justify-content: center;
      border-radius: 50%;
      &.primary{
        background-color: ${({theme:t})=>t["primary-color"]};
      }
      &.success{
        background-color: ${({theme:t})=>t["success-color"]};
      }
      &.info{
        background-color: ${({theme:t})=>t["info-color"]};
      }
    }
    .sDash-support-link-item__title{
      font-size: 20px;
      font-weight: 500;
      color: ${({theme:t})=>t["dark-color"]};
    }
    .sDash-support-link-item__content{
      .btn-link{
        display: flex;
        align-items: center;
        justify-content: center;
        height: 40px;
        background-color: #EFEFFE;
        border-radius: 20px;
        margin: 36px auto 0;
        font-size: 15px;
        padding: 0 24.5px;
        width: fit-content;
      }
    }
  }
  .sDash_faq-block{
    margin-top: 70px;
    @media only screen and (max-width: 1150px){
      margin-top: 20px;
    }
    .ant-card{
      border: 1px solid ${({theme:t})=>t["border-color-normal"]};
      .ant-card-body{
        h1{
          color: ${({theme:t})=>t["dark-color"]};
        }
      }
    }
    .ant-collapse-item {
      &.ant-collapse-item-active{
        box-shadow: 0 0;
      }
      .ant-collapse-header{
        background-color: #fff !important;
        padding: 19.5px 25px !important
        .ant-collapse-arrow{
          color: #9299B8;
        }
      }
      .ant-collapse-content{
        box-shadow: 0 0;
      }
    }
  }
`,n.ZP.div`
    margin-top: 28px;
    @media only screen and (max-width: 575px){
        margin-top: 15px;
    }
    .ant-select-arrow{
        ${({theme:t})=>t.rtl?"left":"right"}: 11px;
    }

    .ant-table table {
        text-align: ${({theme:t})=>t.rtl?"left":"right"};
    }
    .add-product-block{
        background: ${({theme:t})=>t["bg-color-light"]};
        border-radius: 20px;
        padding: 30px;
        border: 1px solid ${({theme:t})=>t["bg-color-light"]};
        @media only screen and (max-width: 575px){
            padding: 20px;
        }
        &:not(:last-child){
            margin-bottom: 30px;
        }
        .ant-card{
            margin-bottom: 0 !important;
            border-radius: 20px;
        }
        .add-product-content{
            box-shadow: 0 10px 30px ${({theme:t})=>t["light-color"]}10;
            border-radius: 20px;
            .ant-card-head{
                padding: 0 40px !important;
                border-radius: ${({theme:t})=>t.rtl?"20px 0 0 20px":"20px 20px 0 0"};
                @media only screen and (max-width: 575px){
                    padding: 0 15px !important;
                }
            }
            .ant-card-head-title{
                padding: 26px 0 25px;
            }
            .ant-card-body{
                padding: 26px 40px 40px !important;
                @media only screen and (max-width: 575px){
                    padding: 20px !important;
                }
            }
        }
        .ant-upload{
            border-spacing: 6px;
            border-width: 2px;
            border-radius: 10px;
            background: ${({theme:t})=>t["bg-color-light"]};
            border-color: ${({theme:t})=>t["border-color-deep"]};
            padding: 50px;
            @media only screen and (max-width: 575px){
                padding: 15px !important;
            }
            .ant-upload-drag-icon{
                i,
                svg{
                    color: ${({theme:t})=>t["extra-light-color"]};
                }
            }
            .ant-upload-text{
                font-weight: 500;
                margin-bottom: 8px;
            }
            .ant-upload-hint{
                font-size: 15px;
                font-weight: 500;
                color: ${({theme:t})=>t["gray-color"]};
                span{
                    color: ${({theme:t})=>t["secondary-color"]};
                }
            }
        }
        .ant-upload-list-item{
            height: 100%;
            padding: 0;
            border: 0 none;
            margin-top: 25px;
        }
        .ant-upload-list-item-info{
            height: 100%;
            >span{
                display: flex;
                align-items: center;
            }
            .ant-upload-list-item-name{
                padding: 0 10px;
                font-weight: 500;
                color: ${({theme:t})=>t["dark-color"]};
                &:hover{
                    color: ${({theme:t})=>t["primary-color"]};
                }
            }
            .ant-upload-list-item-card-actions{
                position: relative;
                top: 0;
                i,
                svg{
                    width: 15px;
                    color: ${({theme:t})=>t["danger-color"]};
                }
            }
            .ant-upload-list-item-thumbnail{
                position: relative;
                top: 0;
                min-width: 100px;
                width: auto;
                height: 100%;
                img{
                    max-width: 100px;
                    width: 100%;
                    height: 100%;
                    border-radius: 6px;
                }
            }
        }
    }
    .add-form-action{
        text-align: ${({theme:t})=>t.rtl?"left":"right"};
        margin-top: 40px;
        .ant-form-item-control-input{
            button{
                height: 50px;
                padding: 0 22.82px;
            }
        }
        button{
            font-size: 15px;
            font-weight: 400;
            height: 50px;
        }
        .btn-cancel{
            border: 1px solid ${({theme:t})=>t["border-color-light"]};
            ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 20px;
            background: ${({theme:t})=>t["bg-color-light"]};
        }
    }
`)},47156:function(t,e,o){t.exports=o.p+"img/arrow-left.1e3c2008.svg"},84908:function(t,e,o){t.exports=o.p+"img/arrow-right.90e07b59.svg"}},e={};function o(n){var a=e[n];if(void 0!==a)return a.exports;var r=e[n]={exports:{}};return t[n].call(r.exports,r,r.exports,o),r.exports}o.m=t,function(){o.amdO={}}(),function(){var t="function"===typeof Symbol?Symbol("webpack queues"):"__webpack_queues__",e="function"===typeof Symbol?Symbol("webpack exports"):"__webpack_exports__",n="function"===typeof Symbol?Symbol("webpack error"):"__webpack_error__",a=function(t){t&&t.d<1&&(t.d=1,t.forEach((function(t){t.r--})),t.forEach((function(t){t.r--?t.r++:t()})))},r=function(o){return o.map((function(o){if(null!==o&&"object"===typeof o){if(o[t])return o;if(o.then){var r=[];r.d=0,o.then((function(t){i[e]=t,a(r)}),(function(t){i[n]=t,a(r)}));var i={};return i[t]=function(t){t(r)},i}}var l={};return l[t]=function(){},l[e]=o,l}))};o.a=function(o,i,l){var d;l&&((d=[]).d=-1);var p,s,c,m=new Set,g=o.exports,h=new Promise((function(t,e){c=e,s=t}));h[e]=g,h[t]=function(t){d&&t(d),m.forEach(t),h["catch"]((function(){}))},o.exports=h,i((function(o){var a;p=r(o);var i=function(){return p.map((function(t){if(t[n])throw t[n];return t[e]}))},l=new Promise((function(e){a=function(){e(i)},a.r=0;var o=function(t){t!==d&&!m.has(t)&&(m.add(t),t&&!t.d&&(a.r++,t.push(a)))};p.map((function(e){e[t](o)}))}));return a.r?l:i()}),(function(t){t?c(h[n]=t):s(g),a(d)})),d&&d.d<0&&(d.d=0)}}(),function(){var t=[];o.O=function(e,n,a,r){if(!n){var i=1/0;for(s=0;s<t.length;s++){n=t[s][0],a=t[s][1],r=t[s][2];for(var l=!0,d=0;d<n.length;d++)(!1&r||i>=r)&&Object.keys(o.O).every((function(t){return o.O[t](n[d])}))?n.splice(d--,1):(l=!1,r<i&&(i=r));if(l){t.splice(s--,1);var p=a();void 0!==p&&(e=p)}}return e}r=r||0;for(var s=t.length;s>0&&t[s-1][2]>r;s--)t[s]=t[s-1];t[s]=[n,a,r]}}(),function(){o.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return o.d(e,{a:e}),e}}(),function(){o.d=function(t,e){for(var n in e)o.o(e,n)&&!o.o(t,n)&&Object.defineProperty(t,n,{enumerable:!0,get:e[n]})}}(),function(){o.f={},o.e=function(t){return Promise.all(Object.keys(o.f).reduce((function(e,n){return o.f[n](t,e),e}),[]))}}(),function(){o.u=function(t){return"js/"+({27:"403",192:"Blank",328:"admin",576:"DeviceList",596:"404",616:"forgotPassword",685:"register",817:"AddApproval",818:"auth"}[t]||t)+"."+{10:"bdfc630f",27:"b1c39b51",192:"41a5136a",316:"a848fe9f",328:"ccda5e5e",576:"1ab8e5b5",596:"61906790",616:"e9e6d906",685:"b8ababea",817:"50b8babf",818:"1c43311a",984:"de287b6a"}[t]+".js"}}(),function(){o.miniCssF=function(t){return"css/admin.36f5ee01.css"}}(),function(){o.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(t){if("object"===typeof window)return window}}()}(),function(){o.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)}}(),function(){var t={},e="strikingdash-vue:";o.l=function(n,a,r,i){if(t[n])t[n].push(a);else{var l,d;if(void 0!==r)for(var p=document.getElementsByTagName("script"),s=0;s<p.length;s++){var c=p[s];if(c.getAttribute("src")==n||c.getAttribute("data-webpack")==e+r){l=c;break}}l||(d=!0,l=document.createElement("script"),l.charset="utf-8",l.timeout=120,o.nc&&l.setAttribute("nonce",o.nc),l.setAttribute("data-webpack",e+r),l.src=n),t[n]=[a];var m=function(e,o){l.onerror=l.onload=null,clearTimeout(g);var a=t[n];if(delete t[n],l.parentNode&&l.parentNode.removeChild(l),a&&a.forEach((function(t){return t(o)})),e)return e(o)},g=setTimeout(m.bind(null,void 0,{type:"timeout",target:l}),12e4);l.onerror=m.bind(null,l.onerror),l.onload=m.bind(null,l.onload),d&&document.head.appendChild(l)}}}(),function(){o.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})}}(),function(){o.p="/"}(),function(){if("undefined"!==typeof document){var t=function(t,e,o,n,a){var r=document.createElement("link");r.rel="stylesheet",r.type="text/css";var i=function(o){if(r.onerror=r.onload=null,"load"===o.type)n();else{var i=o&&("load"===o.type?"missing":o.type),l=o&&o.target&&o.target.href||e,d=new Error("Loading CSS chunk "+t+" failed.\n("+l+")");d.code="CSS_CHUNK_LOAD_FAILED",d.type=i,d.request=l,r.parentNode&&r.parentNode.removeChild(r),a(d)}};return r.onerror=r.onload=i,r.href=e,o?o.parentNode.insertBefore(r,o.nextSibling):document.head.appendChild(r),r},e=function(t,e){for(var o=document.getElementsByTagName("link"),n=0;n<o.length;n++){var a=o[n],r=a.getAttribute("data-href")||a.getAttribute("href");if("stylesheet"===a.rel&&(r===t||r===e))return a}var i=document.getElementsByTagName("style");for(n=0;n<i.length;n++){a=i[n],r=a.getAttribute("data-href");if(r===t||r===e)return a}},n=function(n){return new Promise((function(a,r){var i=o.miniCssF(n),l=o.p+i;if(e(i,l))return a();t(n,l,null,a,r)}))},a={143:0};o.f.miniCss=function(t,e){var o={328:1};a[t]?e.push(a[t]):0!==a[t]&&o[t]&&e.push(a[t]=n(t).then((function(){a[t]=0}),(function(e){throw delete a[t],e})))}}}(),function(){var t={143:0};o.f.j=function(e,n){var a=o.o(t,e)?t[e]:void 0;if(0!==a)if(a)n.push(a[2]);else{var r=new Promise((function(o,n){a=t[e]=[o,n]}));n.push(a[2]=r);var i=o.p+o.u(e),l=new Error,d=function(n){if(o.o(t,e)&&(a=t[e],0!==a&&(t[e]=void 0),a)){var r=n&&("load"===n.type?"missing":n.type),i=n&&n.target&&n.target.src;l.message="Loading chunk "+e+" failed.\n("+r+": "+i+")",l.name="ChunkLoadError",l.type=r,l.request=i,a[1](l)}};o.l(i,d,"chunk-"+e,e)}},o.O.j=function(e){return 0===t[e]};var e=function(e,n){var a,r,i=n[0],l=n[1],d=n[2],p=0;if(i.some((function(e){return 0!==t[e]}))){for(a in l)o.o(l,a)&&(o.m[a]=l[a]);if(d)var s=d(o)}for(e&&e(n);p<i.length;p++)r=i[p],o.o(t,r)&&t[r]&&t[r][0](),t[r]=0;return o.O(s)},n=self["webpackChunkstrikingdash_vue"]=self["webpackChunkstrikingdash_vue"]||[];n.forEach(e.bind(null,0)),n.push=e.bind(null,n.push.bind(n))}();var n=o.O(void 0,[998],(function(){return o(59992)}));n=o.O(n)})();
//# sourceMappingURL=app.7e949e76.js.map