"use strict";(self["webpackChunkstrikingdash_vue"]=self["webpackChunkstrikingdash_vue"]||[]).push([[535],{6626:function(t,e,n){n.d(e,{c:function(){return a}});var i=n(23481);const a=i.ZP.div`
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
    color: ${({theme:t})=>t["gray-color"]};
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
          color: ${({theme:t})=>t["extra-light-color"]};
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
      color: ${({theme:t})=>t["gray-solid"]};
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
        background: ${({theme:t})=>t["border-color-light"]};
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
            box-shadow: 0 5px 15px ${({theme:t})=>t["light-color"]}10;
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
          background: ${({theme:t})=>t["bg-color-light"]};
          color: ${({theme:t})=>t["text-color"]};
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
          background: ${({theme:t})=>t["bg-color-light"]};
          color: ${({theme:t})=>t["text-color"]};
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
          background: ${({theme:t})=>t["bg-color-light"]};
          color: ${({theme:t})=>t["text-color"]};
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
`},92518:function(t,e,n){n.r(e),n.d(e,{default:function(){return f}});var i=n(66252),a=n(3577);const o=(0,i._)("p",{class:"auth-notice"}," 신규 가입은 문의해주세요. ",-1),r={class:"auth-contents"},l={class:"auth-form-action"};function d(t,e,n,d,p,s){const m=(0,i.up)("a-input"),x=(0,i.up)("a-form-item"),g=(0,i.up)("router-link"),c=(0,i.up)("sdButton"),h=(0,i.up)("a-form"),u=(0,i.up)("AuthWrapper");return(0,i.wg)(),(0,i.j4)(u,null,{default:(0,i.w5)((()=>[o,(0,i._)("div",r,[(0,i.Wm)(h,{onFinish:t.handleSubmit,model:t.formState,layout:"vertical"},{default:(0,i.w5)((()=>[(0,i.Wm)(x,{name:"username",label:"Email Address"},{default:(0,i.w5)((()=>[(0,i.Wm)(m,{type:"email",value:t.formState.email,"onUpdate:value":e[0]||(e[0]=e=>t.formState.email=e)},null,8,["value"])])),_:1}),(0,i.Wm)(x,{name:"password",initialValue:"123456",label:"Password"},{default:(0,i.w5)((()=>[(0,i.Wm)(m,{type:"password",value:t.formState.password,"onUpdate:value":e[1]||(e[1]=e=>t.formState.password=e),placeholder:"Password"},null,8,["value"])])),_:1}),(0,i._)("div",l,[(0,i.Wm)(g,{class:"forgot-pass-link",to:"/auth/forgotPassword"},{default:(0,i.w5)((()=>[(0,i.Uk)(" Forgot password? ")])),_:1})]),(0,i.Wm)(x,null,{default:(0,i.w5)((()=>[(0,i.Wm)(c,{class:"btn-signin",htmlType:"submit",type:"primary",size:"large"},{default:(0,i.w5)((()=>[(0,i.Uk)((0,a.zw)(t.isLoading?"Loading...":"Sign In With SSO"),1)])),_:1})])),_:1})])),_:1},8,["onFinish","model"])])])),_:1})}n(70560);var p=n(2262),s=n(33907),m=n(6626),x=n(22201);const g=(0,i.aZ)({name:"SignIn",components:{AuthWrapper:m.c},setup(){const{state:t,dispatch:e}=(0,s.oR)(),n=(0,i.Fl)((()=>t.auth.loading)),a=(0,p.iH)(null),o=(0,x.tv)(),r=()=>{o.push("/"),e("login")},l=t=>{t.value=t},d=(0,p.qj)({email:"example@email.com",password:"1234565"});return{isLoading:n,checked:a,handleSubmit:r,onChange:l,formState:d}}});var c=g,h=n(83744);const u=(0,h.Z)(c,[["render",d]]);var f=u}}]);
//# sourceMappingURL=login.27758be9.js.map