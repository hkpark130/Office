"use strict";(self["webpackChunkstrikingdash_vue"]=self["webpackChunkstrikingdash_vue"]||[]).push([[616],{6626:function(e,t,n){n.d(t,{c:function(){return o}});var i=n(23481);const o=i.ZP.div`
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
`},76468:function(e,t,n){n.r(t),n.d(t,{default:function(){return c}});var i=n(66252);const o={class:"auth-contents"},a=(0,i._)("p",{class:"forgot-text"}," Enter the email address you used when you joined and we’ll send you instructions to reset your password. ",-1),r={class:"return-text"};function l(e,t,n,l,d,p){const s=(0,i.up)("sdHeading"),x=(0,i.up)("a-input"),m=(0,i.up)("a-form-item"),g=(0,i.up)("sdButton"),c=(0,i.up)("router-link"),u=(0,i.up)("a-form"),h=(0,i.up)("AuthWrapper");return(0,i.wg)(),(0,i.j4)(h,null,{default:(0,i.w5)((()=>[(0,i._)("div",o,[(0,i.Wm)(u,{onFinish:e.handleSubmit,model:e.formState,layout:"vertical"},{default:(0,i.w5)((()=>[(0,i.Wm)(s,{as:"h3"},{default:(0,i.w5)((()=>[(0,i.Uk)("Forgot Password?")])),_:1}),a,(0,i.Wm)(m,{label:"Email Address",name:"email"},{default:(0,i.w5)((()=>[(0,i.Wm)(x,{type:"email",value:e.formState.email,"onUpdate:value":t[0]||(t[0]=t=>e.formState.email=t),placeholder:"name@example.com"},null,8,["value"])])),_:1}),(0,i.Wm)(m,null,{default:(0,i.w5)((()=>[(0,i.Wm)(g,{class:"btn-reset",htmlType:"submit",type:"primary",size:"large"},{default:(0,i.w5)((()=>[(0,i.Uk)(" Send Reset Instructions ")])),_:1})])),_:1}),(0,i._)("p",r,[(0,i.Uk)("Return to "),(0,i.Wm)(c,{to:"/auth/login"},{default:(0,i.w5)((()=>[(0,i.Uk)("Sign In")])),_:1})])])),_:1},8,["onFinish","model"])])])),_:1})}var d=n(2262),p=n(6626);const s=(0,i.aZ)({name:"ForgotPassword",components:{AuthWrapper:p.c},setup(){const e=e=>{console.log(e)},t=(0,d.qj)({email:""});return{handleSubmit:e,formState:t}}});var x=s,m=n(83744);const g=(0,m.Z)(x,[["render",l]]);var c=g}}]);
//# sourceMappingURL=forgotPassword.e9e6d906.js.map