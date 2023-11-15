"use strict";(self["webpackChunkstrikingdash_vue"]=self["webpackChunkstrikingdash_vue"]||[]).push([[685],{6626:function(e,t,n){n.d(t,{c:function(){return i}});var a=n(23481);const i=a.ZP.div`
  height: 100%;
  padding: 40px;
  @media only screen and (max-width: 1599px){
    padding: 25px;
  }

  @media only screen and (max-width: 767px){
    text-align: center;
  }
  .auth-notice{
    text-align: right;
    font-weight: 500;
    color: ${({theme:e})=>e["gray-color"]};
    @media only screen and (max-width: 767px){
      text-align: center;
      margin-bottom: 10px;
    }
  }
  button{
    &.btn-signin{
      min-width: 185px;
    }
    &.btn-create{
      border-radius: 8px;
      min-width: 205px;
    }
    &.btn-reset{
      border-radius: 8px;
      min-width: 260px;
    }
    &.ant-btn-lg{
      font-size: 14px;
      font-weight: 500;
      height: 48px;
    }
  }
  .auth-contents{
    display: flex;
    align-items: center;
    justify-content: center;
    form{
      width: 420px;
      h1{
        font-size: 24px;
        font-weight: 600;
        margin-bottom: 45px;
        @media only screen and (max-width: 767px){
          margin-bottom: 28px;
        }
        input::placeholder{
          color: ${({theme:e})=>e["extra-light-color"]};
        }
      }
      .ant-input{
        padding: 12px 11px;
      }
      .auth-form-action{
        margin-bottom: 20px;
        display: flex;
        justify-content: space-between;
        @media only screen and (max-width: 379px){
          flex-flow: column;
          .forgot-pass-link{
            margin-top: 15px;
          }
        }
      }
    }
    .ant-form{
      .ant-form-item{
        margin-bottom: 14px;
      }
    }
    #forgotPass{
      .forgot-text{
        margin-bottom: 25px;
      }
      .return-text{
        margin-top: 35px;
      }
    }
    .form-divider{
      font-size: 13px;
      color: ${({theme:e})=>e["gray-solid"]};
      text-align: center;
      position: relative;
      margin-bottom: 25px;
      &:before{
        content: '';
        position: absolute;
        width: 100%;
        left: 0;
        top: 50%;
        transform: translateY(-50%);
        z-index: 1;
        height: 1px;
        background: ${({theme:e})=>e["border-color-light"]};
      }
      span{
        background: #fff;
        padding: 0 15px;
        display: inline-block;
        position: relative;
        z-index: 2;
      }
    }
    .social-login{
      display: flex;
      align-items: center;
      margin: -6px -6px 14px -6px;
      @media only screen and (max-width: 767px){
        justify-content: center;
      }
      &.signin-social{
        li{
          a{
            box-shadow: 0 5px 15px ${({theme:e})=>e["light-color"]}10;
            background-color: #fff;
          }
        }
      }
      li{
        padding:6px;
        a{
          display: flex;
          align-items: center;
          justify-content: center;
          border-radius: 6px;
          height: 48px;
          padding: 0 15px;
          background: ${({theme:e})=>e["bg-color-light"]};
          color: ${({theme:e})=>e["text-color"]};
          font-weight: 500;
          @media only screen and (max-width: 379px){
            height: 44px;
            padding: 0 12px;
          }
          span:not(.anticon){
            display: inline-block;
            margin-left: 5px;
          }
          svg,
          i{
            width: 20px;
            height: 20px;
          }
          &.google-signup,
          &.google-signin{
            display: flex;
            align-items: center;
            padding: 0 30px;
            @media only screen and (max-width: 379px){
              padding: 0 5px;
            }
            img{
              margin-right: 8px;
              @media only screen and (max-width: 379px){
                margin-right: 4px;
              }
            }
          }
          &.facebook-sign{
            color: #475993;
          }
          &.twitter-sign{
            color: #03A9F4;
          }
        }
      }
    }
    .auth0-login{
      margin: -6px;
      display: flex;
      flex-wrap: wrap;
      a{
          display: inline-flex;
          align-items: center;
          justify-content: center;
          border-radius: 6px;
          height: 48px;
          padding: 0 30px;
          background: ${({theme:e})=>e["bg-color-light"]};
          color: ${({theme:e})=>e["text-color"]};
          font-weight: 500;
          border: 0 none;
          border-radius: 5px;
          margin: 6px;
          flex: 1;
          @media (max-width:480px){
            flex: none;
            width: 100%;
          }
      }
    }
  }
  .auth0-login{
      margin: -6px;
      display: flex;
      flex-wrap: wrap;
      a{
          display: inline-flex;
          align-items: center;
          justify-content: center;
          border-radius: 6px;
          height: 48px;
          padding: 0 30px;
          background: ${({theme:e})=>e["bg-color-light"]};
          color: ${({theme:e})=>e["text-color"]};
          font-weight: 500;
          border: 0 none;
          border-radius: 5px;
          margin: 6px;
          flex: 1;
          @media (max-width:480px){
            flex: none;
            width: 100%;
          }
      }
    }
  }
`},17532:function(e,t,n){n.r(t),n.d(t,{default:function(){return H}});var a=n(66252),i=n(3577);const o={class:"auth-notice"},r={class:"auth-contents"},l=(0,a._)("span",{class:"color-secondary"},"Admin",-1),c={class:"auth-form-action"},d=(0,a._)("p",{class:"form-divider"},[(0,a._)("span",null,"Or")],-1),s={class:"social-login signin-social"},u={class:"google-signup",href:"#"},p=["src"],m=(0,a._)("span",null,"Sign up with Google",-1),g={class:"facebook-sign",href:"#"},f={class:"twitter-sign",href:"#"};function h(e,t,h,x,b,w){const v=(0,a.up)("router-link"),y=(0,a.up)("sdHeading"),A=(0,a.up)("a-input"),k=(0,a.up)("a-form-item"),W=(0,a.up)("a-checkbox"),O=(0,a.up)("sdButton"),S=(0,a.up)("FacebookOutlined"),U=(0,a.up)("TwitterOutlined"),j=(0,a.up)("a-form"),N=(0,a.up)("AuthWrapper");return(0,a.wg)(),(0,a.j4)(N,null,{default:(0,a.w5)((()=>[(0,a._)("p",o,[(0,a.Uk)(" Already have an account? "),(0,a.Wm)(v,{to:"/auth/login"},{default:(0,a.w5)((()=>[(0,a.Uk)("Sign In")])),_:1})]),(0,a._)("div",r,[(0,a.Wm)(j,{name:"register",model:e.formState,onFinish:e.handleSubmit,layout:"vertical"},{default:(0,a.w5)((()=>[(0,a.Wm)(y,{as:"h3"},{default:(0,a.w5)((()=>[(0,a.Uk)(" Sign Up to "),l])),_:1}),(0,a.Wm)(k,{label:"Name",name:"name"},{default:(0,a.w5)((()=>[(0,a.Wm)(A,{value:e.formState.name,"onUpdate:value":t[0]||(t[0]=t=>e.formState.name=t),placeholder:"Full name"},null,8,["value"])])),_:1}),(0,a.Wm)(k,{name:"username",label:"Username"},{default:(0,a.w5)((()=>[(0,a.Wm)(A,{value:e.formState.username,"onUpdate:value":t[1]||(t[1]=t=>e.formState.username=t),placeholder:"Username"},null,8,["value"])])),_:1}),(0,a.Wm)(k,{name:"email",label:"Email Address"},{default:(0,a.w5)((()=>[(0,a.Wm)(A,{type:"email",value:e.formState.email,"onUpdate:value":t[2]||(t[2]=t=>e.formState.email=t),placeholder:"name@example.com"},null,8,["value"])])),_:1}),(0,a.Wm)(k,{label:"Password",name:"password"},{default:(0,a.w5)((()=>[(0,a.Wm)(A,{type:"password",value:e.formState.password,"onUpdate:value":t[3]||(t[3]=t=>e.formState.password=t),placeholder:"Password"},null,8,["value"])])),_:1}),(0,a._)("div",c,[(0,a.Wm)(W,{onChange:e.onChange},{default:(0,a.w5)((()=>[(0,a.Uk)(" Creating an account means you’re okay with our Terms of Service and Privacy Policy ")])),_:1},8,["onChange"])]),(0,a.Wm)(k,null,{default:(0,a.w5)((()=>[(0,a.Wm)(O,{class:"btn-create",htmlType:"submit",type:"primary",size:"large"},{default:(0,a.w5)((()=>[(0,a.Uk)((0,i.zw)(e.isLoading?"Loading...":"Create Account"),1)])),_:1})])),_:1}),d,(0,a._)("ul",s,[(0,a._)("li",null,[(0,a._)("a",u,[(0,a._)("img",{src:n(88004),alt:""},null,8,p),m])]),(0,a._)("li",null,[(0,a._)("a",g,[(0,a.Wm)(S)])]),(0,a._)("li",null,[(0,a._)("a",f,[(0,a.Wm)(U)])])])])),_:1},8,["model","onFinish"])])])),_:1})}var x={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M880 112H144c-17.7 0-32 14.3-32 32v736c0 17.7 14.3 32 32 32h736c17.7 0 32-14.3 32-32V144c0-17.7-14.3-32-32-32zm-32 736H663.9V602.2h104l15.6-120.7H663.9v-77.1c0-35 9.7-58.8 59.8-58.8h63.9v-108c-11.1-1.5-49-4.8-93.2-4.8-92.2 0-155.3 56.3-155.3 159.6v89H434.9v120.7h104.3V848H176V176h672v672z"}}]},name:"facebook",theme:"outlined"},b=x,w=n(22130);function v(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?Object(arguments[t]):{},a=Object.keys(n);"function"===typeof Object.getOwnPropertySymbols&&(a=a.concat(Object.getOwnPropertySymbols(n).filter((function(e){return Object.getOwnPropertyDescriptor(n,e).enumerable})))),a.forEach((function(t){y(e,t,n[t])}))}return e}function y(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}var A=function(e,t){var n=v({},e,t.attrs);return(0,a.Wm)(w.Z,v({},n,{icon:b}),null)};A.displayName="FacebookOutlined",A.inheritAttrs=!1;var k=A,W={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M928 254.3c-30.6 13.2-63.9 22.7-98.2 26.4a170.1 170.1 0 0075-94 336.64 336.64 0 01-108.2 41.2A170.1 170.1 0 00672 174c-94.5 0-170.5 76.6-170.5 170.6 0 13.2 1.6 26.4 4.2 39.1-141.5-7.4-267.7-75-351.6-178.5a169.32 169.32 0 00-23.2 86.1c0 59.2 30.1 111.4 76 142.1a172 172 0 01-77.1-21.7v2.1c0 82.9 58.6 151.6 136.7 167.4a180.6 180.6 0 01-44.9 5.8c-11.1 0-21.6-1.1-32.2-2.6C211 652 273.9 701.1 348.8 702.7c-58.6 45.9-132 72.9-211.7 72.9-14.3 0-27.5-.5-41.2-2.1C171.5 822 261.2 850 357.8 850 671.4 850 843 590.2 843 364.7c0-7.4 0-14.8-.5-22.2 33.2-24.3 62.3-54.4 85.5-88.2z"}}]},name:"twitter",theme:"outlined"},O=W;function S(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?Object(arguments[t]):{},a=Object.keys(n);"function"===typeof Object.getOwnPropertySymbols&&(a=a.concat(Object.getOwnPropertySymbols(n).filter((function(e){return Object.getOwnPropertyDescriptor(n,e).enumerable})))),a.forEach((function(t){U(e,t,n[t])}))}return e}function U(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}var j=function(e,t){var n=S({},e,t.attrs);return(0,a.Wm)(w.Z,S({},n,{icon:O}),null)};j.displayName="TwitterOutlined",j.inheritAttrs=!1;var N=j,_=n(6626),Q=n(2262),B=n(33907);const P=(0,a.aZ)({name:"SignUp",components:{FacebookOutlined:k,TwitterOutlined:N,AuthWrapper:_.c},setup(){const e=(0,Q.iH)(null),t=(0,Q.iH)(null),{state:n}=(0,B.oR)(),i=(0,a.Fl)((()=>n.auth.loading)),o=t=>{e.value=t},r=e=>{t.value=e},l=(0,Q.qj)({name:"",username:"",email:"",password:""});return{onChange:r,handleSubmit:o,formState:l,isLoading:i}}});var C=P,L=n(83744);const V=(0,L.Z)(C,[["render",h]]);var H=V},88004:function(e){e.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAAABHNCSVQICAgIfAhkiAAAAjdJREFUOE+1lL1rU1EYxp/3NrEW2qSiFJWqRaRmEJqlOpq02iXU3m5u1q/ByVTddRGkQ1r/AU1ABKcEIh2alN5RWoVUB4suCW66JLUQSW7uI+eWXG4+SQTvdM573vt7n/fjHEGHb39meskSWQARFMGEciORF9AQEcO3uZ1o96s0G/dnL+sWsVqHdAqo4Jpg2be5nXL7NABLs9NxQG51gjTbCZYGa9XgkJHL188cYN8wYtdvVUJi5IotClWaBJKtypjQhHGPadoKqgNHggR1tfbVqtFmmLLbCg/uTn6o5Uev1IEqFU8NoWFjJ9dr+k7KZhY6iWQlcxbmp5O2XRMrPJL9aPQLsxVWM1BdWlAb8/MJVLfGX/ve5+78C+wQuAEDgqsuQNh7HS3q9NifNQBTXQMRRaWQbiePB8ckjIbOqfPFWNkAxB24Lfs/AHtMuSeFZKGhKV+tEbwwA+/eRnZu9tIUPVaOi+tmEUxIfWxStdNYMS/iN7wArfDefLbr2Oir5ZBQttyBSd62B3tl/cKXV9b5S85gk0VLQ/h7JNN2sBUMlKQAow6QLCQfD03YwED6WgiiNUQ7dGQcGuKQgYK9s8wpoehjP+8vHS/eaKgKLVlMPRlMOY9DID33DIKnvdRO+fhLMxj7dQ8ea1jN3cvUo6NR5y7XIf1CvZVTB2d+PH+z/nD8gXOXmxVNpud0TbgGyLnualmwKNFv8xudH1g3QNWVokWFHKUgKBA/iV0R5CA09iKZeLuAfwEBsO2jMmMBgQAAAABJRU5ErkJggg=="}}]);
//# sourceMappingURL=register.b8ababea.js.map