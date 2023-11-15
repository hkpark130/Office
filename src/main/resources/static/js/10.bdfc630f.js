"use strict";(self["webpackChunkstrikingdash_vue"]=self["webpackChunkstrikingdash_vue"]||[]).push([[10],{52143:function(t,e,i){i.d(e,{VY:function(){return x},Yl:function(){return l},fo:function(){return r},rN:function(){return p},rS:function(){return n},xN:function(){return h},y_:function(){return d}});var a=i(23481);const o=["hide","searchHide","darkMode","topMenu"],n=(0,a.ZP)("div",o)`
    position: relative;
    header{
        box-shadow: 0 2px 30px ${({theme:t})=>t["gray-solid"]}10;
        ${({darkMode:t})=>t?"background: #272B41;":""};
        z-index: 999;

        .ant-btn-white{
            ${({darkMode:t})=>t?"background: #272B41 !important; border-color: #272B41 !important; color: #7D808D !important":""};
        }

        .head-example{
            ${({darkMode:t})=>t?"color: #A8AAB3;":""};
        }
        .ant-menu-sub.ant-menu-vertical{
            .ant-menu-item{
                a{
                    color: ${({theme:t})=>t["gray-color"]};
                }
            }
        }
        .ant-menu.ant-menu-horizontal{
            display: flex;
            align-items: center;
            margin: 0 -16px;
            li.ant-menu-submenu{
                margin: 0 16px;
            }
            .ant-menu-submenu{
                &.ant-menu-submenu-active,
                &.ant-menu-submenu-selected,
                &.ant-menu-submenu-open{
                    .ant-menu-submenu-title{
                        color: ${({darkMode:t})=>t?"#fff;":"#5A5F7D"};
                        svg,
                        i{
                            color: ${({darkMode:t})=>t?"#fff;":"#5A5F7D"};
                        }
                    }
                }
                .ant-menu-submenu-title{
                    font-size: 14px;
                    font-weight: 500;
                    color: ${({darkMode:t})=>t?"#ffffff90;":"#5A5F7D"};
                    svg,
                    i{
                        color: ${({darkMode:t})=>t?"#ffffff90;":"#5A5F7D"};
                    }
                    .ant-menu-submenu-arrow{
                        font-family: "FontAwesome";
                        font-style: normal;
                        ${({theme:t})=>t.rtl?"margin-right":"margin-left"}: 6px;
                        &:after{
                            color: ${({darkMode:t})=>t?"#ffffff90;":"#9299B8"};
                            content: '\f107';
                            background-color: transparent;
                        }
                    }
                }
            }
        }


    }
    .header-more{
        .head-example{
            ${({darkMode:t})=>t?"color: #A8AAB3;":""};
        }
    }
    .customizer-trigger{
        display: flex;
        align-items: center;
        justify-content: center;
        width: 50px;
        height: 50px;
        border-radius: ${({theme:t})=>t.rtl?"0 10px 10px 0":"10px 0 0 10px"};
        background-color: #5F63F2;
        position: fixed;
        ${({theme:t})=>t.rtl?"left":"right"}: 0;
        top: 50%;
        transform: translateY(-50%);
        transition: all .3s ease;
        z-index: 999;
        box-shadow: 0 10px 15px rgba(#5F63F2,.20);
        &.show{
            ${({theme:t})=>t.rtl?"left":"right"}: 295px;
        }
        svg,
        img{
            width: 20px;
            color: #fff;
            animation: antRotate 3s infinite linear;
        }
    }
    .customizer-wrapper{
        position: fixed;
        top: 0;
        ${({theme:t})=>t.rtl?"left":"right"}: 0;
        width: 350px;
        transform: translateX(${({theme:t})=>t.rtl?"-350px":"350px"});
        height: 100vh;
        overflow-y: auto;
        background-color: #fff;
        box-shadow: 0 0 30px #9299B810;
        z-index: 99999999999;
        transition: all .3s ease;
        @media only screen and (max-width: 479px){
            width: 280px;
            transform: translateX(${({theme:t})=>t.rtl?"-280px":"280px"});
        }
        &.show{
            transform: translateX(0);
        }
    }
    .customizer{
        height: 100%;
        .customizer__head{
            position: relative;
            padding: 18px 24px;
            border-bottom: 1px solid #f0f0f0;
            text-align: left;
            .customizer-close{
                position: absolute;
                right: 15px;
                top: 15px;
                svg,
                i{
                    color: #FF4D4F;
                }
            }
            .customizer__title{
                font-weight: 600;
                color: #272B41;
                font-size: 16px;
                margin-bottom: 2px;
            }
        }
        .customizer__body{
            padding: 25px;
        }
        .customizer__single{
            &:not(:last-child){
                margin-bottom: 35px;
            }
            h4{
                font-weight: 600;
                font-size: 16px;
                margin-bottom: 10px;
                color: #272B41;
            }
        }
    }
    .customizer-list{
        margin: -10px;
        .customizer-list__item{
            position: relative;
            display: inline-block;
            min-height: 60px;
            background-size: cover;
            margin: 10px;
            &.top{
                span.fa{
                    top: 35px;
                }
            }
            &:hover{
                span{
                    color: #5F63F2;
                }
            }
            a{
                position: relative;
                display: block;
                &.active{
                    span.fa,
                    svg{
                        display: block;
                    }
                }
                span.fa,
                svg{
                    display: none;
                    font-size: 14px;
                    margin-top: 0;
                    position: absolute;
                    top: 15px;
                    right: 15px;
                    color: ${({theme:t})=>t["success-color"]};
                }
            }
            img{
                width: 100%;
            }
            span{
                display: inline-block;
                margin-top: 15px;
                color: #272B41;
            }
        }
    }
    .striking-logo{
        @media only screen and (max-width: 875px){
            ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 4px;
        }
        @media only screen and (max-width: 767px){
            ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 0;
        }
        img{
            max-width: ${({theme:t})=>t.topMenu?"140px":"120px"};
            width: 100%;
        }
        &.top-menu{
            ${({theme:t})=>t.rtl?"margin-right":"margin-left"}: 15px;
        }
    }
    .certain-category-search-wrapper{
        ${({darkMode:t,theme:e})=>t?(e.rtl?"border-left":"border-right")+": 1px solid #272B41;":""};
         @media only screen and (max-width: 767px){
            padding: 0 15px;
        }
        input{
            max-width: 350px;
            ${({darkMode:t})=>t?"background: #272B41;":""};
            ${({darkMode:t})=>t?"color: #fff;":"#5A5F7D"};
            @media only screen and (max-width: 875px){
                ${({theme:t})=>t.rtl?"padding-right":"padding-left"}: 5px;
            }
        }
    }

    .navbar-brand{
        display: flex;
        align-items: center;
        button{
            padding: ${({theme:t})=>t.rtl?"0 15px 0 25px !important":"0 25px 0 15px !important"};
            line-height: 0;0
            color: ${({theme:t})=>t["extra-light-color"]};
            @media only screen and (max-width: 875px){
                padding: ${({theme:t})=>t.rtl?"0 10px 0 25px !important":"0 25px 0 10px !important"};
            }
            @media only screen and (max-width: 767px){
                padding: ${({theme:t})=>t.rtl?"0 0px 0 15px !important":"0 15px 0 0px !important"};
            }
        }
    }

    /* Sidebar styles */
    .ant-layout-sider{
        box-shadow: 0 0 30px #9299B810;
        @media (max-width: 991px){
            box-shadow: 0 0 10px #00000020;
        }
        &.ant-layout-sider-dark{
            background: ${({theme:t})=>t["dark-color"]};
            .ant-layout-sider-children{
                .ant-menu{
                    .ant-menu-submenu-inline{
                        > .ant-menu-submenu-title{
                            padding: 0 15px !important;
                        }
                    }
                    .ant-menu-item{
                        padding: 0 15px !important;
                    }
                }
            }
        }

        .ant-layout-sider-children{
            padding-bottom: 15px;
            >.sidebar-nav-title{
                margin-top: 8px;
            }

            .ant-menu{
                /* overflow-x: hidden; */
                ${({theme:t})=>t.rtl?"border-left":"border-right"}: 0 none;
                ${({theme:t})=>t.rtl?"text-align: right":"text-align: left"};
                box-shadow: none !important;
                .ant-menu-item:not(.ant-menu-item-only-child){
                    span{
                        a{
                            ${({theme:t})=>t.rtl?"padding-right":"padding-left"}: 20px;
                        }
                    }
                }
                .ant-menu-submenu, .ant-menu-item{
                    i{
                        line-height: normal;
                    }
                    .feather,
                    img{
                        width: 16px;
                        font-size: 16px;
                        color: ${({theme:t})=>t["extra-light-color"]};
                    }
                    span{
                        display: inline-block;
                        color: ${({theme:t})=>t["dark-color"]};
                        transition: 0.3s ease;
                        a{
                            ${({theme:t})=>t.rtl?"padding-right":"padding-left"}: 15px;
                            color: ${({theme:t})=>t["dark-color"]};
                        }
                    }
                    .sDash_menu-item-icon{
                        line-height: .6;
                    }
                }
                .ant-menu-submenu{
                    span{
                        ${({theme:t})=>t.rtl?"padding-left":"padding-right"}: 20px;
                        span{
                            ${({theme:t})=>t.rtl?"padding-right":"padding-left"}: 20px;
                        }
                    }
                }
                .ant-menu-item{
                    .menuItem-iocn{
                        width: auto;
                    }
                }
                .ant-menu-item,
                .ant-menu-submenu-title{
                    a{
                        position: relative;
                        display: block !important;
                    }
                    >span{
                        /* width: 100%; */
                        .pl-0{
                            ${({theme:t})=>t.rtl?"padding-right":"padding-left"}: 0px;
                        }
                    }
                    .badge{
                        position: absolute;
                        ${({theme:t})=>t.rtl?"left":"right"}: 30px;
                        top: 50%;
                        transform: translateY(-50%);
                        display: inline-block;
                        height: auto;
                        font-size: 10px;
                        border-radius: 3px;
                        padding: 3px 4px 4px;
                        line-height: 1;
                        letter-spacing: 1px;
                        color: #fff;
                        &.badge-primary{
                            background-color: ${({theme:t})=>t["primary-color"]};
                        }
                        &.badge-success{
                            background-color: ${({theme:t})=>t["success-color"]};
                        }
                    }
                }
                .ant-menu-submenu{
                    .ant-menu-submenu-title{
                        display: flex;
                        align-items: center;
                        .title{
                            padding-left: 0;
                        }
                        .badge{
                            ${({theme:t})=>t.rtl?"left":"right"}: 45px;
                        }
                    }
                }
                .ant-menu-submenu-inline{
                    > .ant-menu-submenu-title{
                        display: flex;
                        align-items: center;
                        padding: 0 15px !important;
                        svg,
                        img{
                            width: 16px;
                            height: 16px;
                        }

                        .ant-menu-submenu-arrow{
                            right: auto;
                            ${({theme:t})=>t.rtl?"left":"right"}: 24px;
                            &:after,
                            &:before{
                                width: 7px;
                                background: #868EAE;
                                height: 1.25px;
                            }
                            &:before{
                                transform: rotate(45deg) ${({theme:t})=>t.rtl?"translateY(3.3px)":"translateY(-3.3px)"};
                            }
                            &:after{
                                transform: rotate(-45deg) ${({theme:t})=>t.rtl?"translateY(-3.3px)":"translateY(3.3px)"};
                            }
                        }
                    }
                    &.ant-menu-submenu-open{
                        > .ant-menu-submenu-title{
                            .ant-menu-submenu-arrow{
                                transform: translateY(2px);
                                &:before{
                                    transform: rotate(45deg) translateX(-3.3px);
                                }
                                &:after{
                                    transform: rotate(-45deg) translateX(3.3px);
                                }
                            }
                        }
                    }
                    .ant-menu-item{
                        ${({theme:t})=>t.rtl?"padding-right":"padding-left"}: 0px !important;
                        ${({theme:t})=>t.rtl?"padding-left":"padding-right"}: 0 !important;
                        transition: all 0.2s cubic-bezier(0.215, 0.61, 0.355, 1) 0s;
                        a{
                            ${({theme:t})=>t.rtl?"padding-right":"padding-left"}: 50px !important;
                        }
                    }
                }
                .ant-menu-item{
                    display: flex;
                    align-items: center;
                    padding: 0 !important;
                    &.ant-menu-item-active{
                        border-radius: 4px;
                        ${({darkMode:t})=>t?"background-color: rgba(255, 255, 255, .05);":""};
                    }
                    a{
                        width: 100%;
                        display: flex !important;
                        align-items: center;
                        /* ${({theme:t})=>t.rtl?"justify-content: flex-end":""}; */
                        .feather{
                            width: 16px;
                            color: ${({theme:t})=>t["extra-light-color"]};
                        }
                        span{
                            ${({theme:t})=>t.rtl?"padding-right":"padding-left"}: 20px;
                            display: inline-block;
                            color: ${({theme:t})=>t["dark-color"]};
                        }
                    }
                    &.ant-menu-item-selected{
                        svg,
                        i{
                            color: ${({theme:t})=>t["primary-color"]};
                        }
                    }
                }
                .ant-menu-submenu,
                .ant-menu-item{
                    ${({theme:t})=>t.rtl&&"padding-right: 5px;"}

                    &.ant-menu-item-selected{
                        border-radius: 4px;
                        &:after{
                            content: none;
                        }
                    }
                    &.ant-menu-submenu-active{
                        border-radius: 4px;
                        ${({darkMode:t})=>t?"background-color: rgba(255, 255, 255, .05);":""};
                    }
                }
                .sidebar-nav-title{
                    margin-top: 40px;
                    margin-bottom: 24px;
                }
                &.ant-menu-inline-collapsed{
                    .ant-menu-submenu{
                        text-align: ${({theme:t})=>t.rtl?"right":"left"};
                        .ant-menu-submenu-title{
                            padding: 0 20px;
                            justify-content: center;
                            .ant-menu-title-content{
                                ${({theme:t})=>t.rtl?"display: inline-flex":""};
                            }
                        }
                    }
                    .ant-menu-item{
                        padding: 0 22px !important;
                        //justify-content: center;
                        a{
                            padding: 0 !important;
                        }
                    }
                    .ant-menu-submenu, .ant-menu-item {
                        // add new class
                        .ant-menu-title-content {
                            span{
                                display: none;
                            }
                        }
                    }
                }
            }
        }



        .sidebar-nav-title{
            font-size: 12px;
            font-weight: 500;
            text-transform: uppercase;
            ${({darkMode:t})=>t?"color: rgba(255, 255, 255, .38);":"color: #9299B8;"};
            padding: 0 ${({theme:t})=>t.rtl?"20px":"15px"};
            display: flex;
            justify-content: ${({theme:t})=>t.rtl?"flex-end":"flex-start"};
        }
        &.ant-layout-sider-collapsed{
            padding: 15px 0px 55px !important;
            .sidebar-nav-title{
                display: none;
            }
            & + .atbd-main-layout{
                ${({theme:t})=>t.rtl?"margin-right":"margin-left"}: 80px;
            }
            .ant-menu-item{
                color: #333;
                .badge{
                    display: none;
                }
            }
            .ant-menu-submenu {
                .ant-menu-sub {
                    opacity: 0;
                }
            }
        }
        .ant-menu-item-group{
            .ant-menu-item-group-title{
                padding: 0;
            }
        }
    }
    @media only screen and (max-width: 1150px){
        .ant-layout-sider.ant-layout-sider-collapsed{
            ${({theme:t})=>t.rtl?"right":"left"}: -80px !important;
        }

    }

    .atbd-main-layout{
    ${({theme:t})=>t.rtl?"margin-right":"margin-left"}: ${({theme:t})=>t.topMenu?0:"280px"};
        margin-top: 64px;
        transition: 0.3s ease;
        @media only screen and (max-width: 1150px){
            ${({theme:t})=>t.rtl?"margin-right":"margin-left"}: auto !important;
        }
    }

    /* Mobile Actions */
    .mobile-action{
        position: absolute;
        ${({theme:t})=>t.rtl?"left":"right"}: 20px;
        top: 50%;
        transform: translateY(-50%);
        display: inline-flex;
        align-items: center;
        @media only screen and (max-width: 767px){
            ${({theme:t})=>t.rtl?"left":"right"}: 0;
        }
        a{
            display: inline-flex;
            color: ${({theme:t})=>t["light-color"]};
            &.btn-search{
                ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 18px;
            }
            svg{
                width: 20px
                height: 20px;
            }
        }
    }
    .admin-footer{
        .admin-footer__copyright{
            display: inline-block;
            width: 100%;
            color: ${({theme:t})=>t["light-color"]};
            @media only screen and (max-width: 767px){
                text-align: center;
                margin-bottom: 10px;
            }
        }
        .admin-footer__links{
            text-align: ${({theme:t})=>t.rtl?"left":"right"};
            @media only screen and (max-width: 767px){
                text-align: center;
            }
            a{
                color: ${({theme:t})=>t["light-color"]};
                &:not(:last-child){
                    ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 15px;
                }
                &:hover{
                    color: ${({theme:t})=>t["primary-color"]};
                }
            }
        }
    }
`,r=(0,a.ZP)("div",o)`
        ${({darkMode:t})=>t?"background: #272B41;":"background: #fff"};
        width: 100%;
        position: fixed;
        margin-top: ${({hide:t})=>t?"0px":"64px"};
        top: 0;
        ${({theme:t})=>t.rtl?"right":"left"}: 0;
        transition: .3s;
        opacity: ${({hide:t})=>t?0:1}
        z-index: ${({hide:t})=>t?-1:1}
        box-shadow: 0 2px 30px #9299b810;

`,l=(0,a.ZP)("div",o)`
        ${({darkMode:t})=>t?"background: #272B41;":"background: #fff"};
        width: 100%;
        position: fixed;
        margin-top: ${({hide:t})=>t?"0px":"64px"};
        top: 0;
        ${({theme:t})=>t.rtl?"right":"left"}: 0;
        transition: .3s;
        opacity: ${({hide:t})=>t?0:1}
        z-index: ${({hide:t})=>t?-1:999}
        box-shadow: 0 2px 30px #9299b810;

`,d=((0,a.ZP)("div",o)`
    background: #ddd;
    width: 200px;
    position: fixed;
    ${({theme:t})=>t.rtl?"left":"right"}: 0;
    top: 50%;
    margin-top: -100px;
    z-index: 9999;
    display: flex;
    flex-direction: column;
    padding: 15px;
    border-radius: 5px;
    button{
        margin-top: 5px;
    }
`,(0,a.ZP)("div",o)`
    .top-right-wrap{
        position: relative;
        float: ${({theme:t})=>t.rtl?"left":"right"};
    }
    .search-toggle{
        display: flex;
        align-items: center;
        ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 10px;
        ${({theme:t})=>t.darkMode?"color: #A8AAB3;":"color :#5A5F7D"};
        .feather-x{
            display: none;
        }
        .feather-search{
            display: flex;
        }
        &.active{
            .feather-search{
                display: none;
            }
            .feather-x{
                display: flex;
            }
        }
        svg,
        img{
            width: 20px;
        }
    }
    .topMenu-search-form{
        position: absolute;
        ${({theme:t})=>t.rtl?"left":"right"}: 100%;
        ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 15px;
        top: 12px;
        background-color: #fff;
        border: 1px solid ${({theme:t})=>t["border-color-normal"]};
        border-radius: 6px;
        height: 40px;
        width: 280px;
        display: none;
        &.show{
            display: block;
        }
        .search-icon{
            width: fit-content;
            line-height: 1;
            position: absolute;
            left: 15px;
            ${({theme:t})=>t.rtl?"right":"left"}: 15px;
            top: 50%;
            transform: translateY(-50%);
            z-index: 9999;
        }
        i,
        svg{
            width: 18px;
            color: ${({theme:t})=>t.darkMode?"color: #A8AAB3;":"color:# 9299b8"};
        }
        form{
            height: auto;
            display: flex;
            align-items: center;
        }
        input{
            position: relative;
            border-radius: 6px;
            width: 100%;
            border: 0 none;
            height: 38px;
            padding-left: 40px;
            z-index: 999;
            ${({theme:t})=>t.rtl?"padding-right":"padding-left"}: 40px;
            &:focus{
                border: 0 none;
                box-shadow: 0 0;
                outline: none;
            }
        }
    }
`),p=(0,a.ZP)("div",o)`
    .strikingDash-top-menu{
        ul{
            li{
                display: inline-block;
                position: relative;
                ${({theme:t})=>t.rtl?"padding-left":"padding-right"}: 14px;
                @media only screen and (max-width: 1024px){
                    ${({theme:t})=>t.rtl?"padding-left":"padding-right"}: 10px;
                }
                &:not(:last-child){
                    ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 34px;
                    @media only screen and (max-width: 1300px){
                        ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 30px;
                    }
                    @media only screen and (max-width: 1199px){
                        ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 26px;
                    }
                    @media only screen and (max-width: 1024px){
                        ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 16px;
                    }
                }
                &.has-subMenu{
                    >a{
                        position: relative;
                        &:before{
                            position: absolute;
                            ${({theme:t})=>t.rtl?"left":"right"}: -14px;
                            top: 50%;
                            transform: translateY(-50%);
                            font-family: "FontAwesome";
                            content: '\f107';
                            line-height: 1;
                            color: ${({theme:t})=>t["light-color"]};
                        }
                        &.active{
                            &:before{
                                ${({theme:t})=>t.darkMode?"color: #fff;":"color: #5F63F2"};
                            }
                        }
                    }
                }
                &.has-subMenu-left{
                    >a{
                        position: relative;
                        &:before{
                            position: absolute;
                            ${({theme:t})=>t.rtl?"left":"right"}: 30px;
                            top: 50%;
                            transform: translateY(-50%);
                            font-family: "FontAwesome";
                            content: '\f105';
                            line-height: 1;
                            color: ${({theme:t})=>t["light-color"]};
                        }
                    }
                }
                &:hover{
                    >.subMenu{
                        top: 64px;
                        opacity: 1;
                        visibility: visible;
                    }
                }
                a{
                    display: flex;
                    align-items: center;
                    font-weight: 500;
                    ${({theme:t})=>t.darkMode?"color: #ffffff60;":"color: #5A5F7D"};
                    &.active{
                        ${({theme:t})=>t.darkMode?"color: #fff;":"color: #5F63F2"};
                    }
                    svg,
                    img,
                    i{
                        ${({theme:t})=>t.rtl?"margin-left":"margin-right"} : 14px;
                        width: 16px;
                    }
                }
                >ul{
                    li{
                        display: block;
                        position: relative;
                        ${({theme:t})=>t.rtl?"padding-left":"padding-right"}: 0;
                        ${({theme:t})=>t.rtl?"margin-left":"margin-right"}: 0 !important;
                        a{
                            font-weight: 400;
                            padding: 0 30px;
                            line-height: 3;
                            color: #868EAE;
                            transition: .3s;
                            &:hover,
                            &.active{
                                color: ${({theme:t})=>t["primary-color"]};
                                background-color: ${({theme:t})=>t["primary-color"]}06;
                                ${({theme:t})=>t.rtl?"padding-right":"padding-left"}: 40px;
                            }
                        }
                        &:hover{
                            .subMenu{
                                top: 0;
                                ${({theme:t})=>t.rtl?"right":"left"}: 250px;
                                @media only screen and (max-width: 1300px){
                                    ${({theme:t})=>t.rtl?"right":"left"}: 180px;
                                }
                            }
                        }
                    }
                }
            }
        }
        .subMenu{
            width: 250px;
            background: #fff;
            border-radius: 6px;
            position: absolute;
            ${({theme:t})=>t.rtl?"right":"left"}: 0;
            top: 80px;
            padding: 12px 0;
            visibility: hidden;
            opacity: 0;
            transition: 0.3s;
            z-index: 98;
            box-shadow: 0px 15px 40px 0px rgba(82, 63, 105, 0.15);
            @media only screen and (max-width: 1300px){
                width: 180px;
            }
            .subMenu{
                width: 250px;
                background: #fff;
                position: absolute;
                ${({theme:t})=>t.rtl?"right":"left"}: 250px;
                top: 0px;
                padding: 12px 0;
                visibility: hidden;
                opacity: 0;
                transition: 0.3s;
                z-index: 98;
                box-shadow: 0px 15px 40px 0px rgba(82, 63, 105, 0.15);
                @media only screen and (max-width: 1300px){
                    width: 200px;
                    ${({theme:t})=>t.rtl?"right":"left"}: 180px;
                }
            }
        }
    }
    /* // Mega Menu */
    .strikingDash-top-menu{
        >ul{
            >li{
                &:hover{
                    .megaMenu-wrapper{
                        opacity: 1;
                        visibility: visible;
                        z-index: 99;
                    }
                }
                &.mega-item{
                    position: static;
                }
                .sDash_menu-item-icon{
                    line-height: .6;
                }
                .megaMenu-wrapper{
                    display: flex;
                    position: absolute;
                    text-align: ${({theme:t})=>t.rtl?"right":"left"}
                    ${({theme:t})=>t.rtl?"right":"left"}: 0;
                    top: 100%;
                    overflow: hidden;
                    z-index: -1;
                    padding: 16px 0;
                    box-shadow: 0px 15px 40px 0px rgba(82, 63, 105, 0.15);
                    border-radius: 0 0 6px 6px;
                    opacity: 0;
                    visibility: hidden;
                    transition: .4s;
                    background-color: #fff;
                    &.megaMenu-small{
                        width: 590px;
                        >li{
                            flex: 0 0 33.3333%;
                        }
                        ul{
                            li{
                                >a{
                                    padding: 0 45px;
                                    position: relative
                                    &:after{
                                        width: 5px;
                                        height: 5px;
                                        border-radius: 50%;
                                        position: absolute;
                                        ${({theme:t})=>t.rtl?"right":"left"}: 30px;
                                        top: 50%;
                                        transform: translateY(-50%);
                                        background-color: #C6D0DC;
                                        content: '';
                                        transition: .3s;
                                    }
                                    &:hover,
                                    &.active{
                                        ${({theme:t})=>t.rtl?"padding-right":"padding-left"}: 45px;
                                        color: ${({theme:t})=>t["primary-color"]};
                                        &:after{
                                            background-color: ${({theme:t})=>t["primary-color"]};;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    &.megaMenu-wide{
                        width: 1000px;
                        padding: 5px 0 18px;
                        @media only screen and (max-width: 1300px){
                            width: 800px;
                        }
                        >li{
                            position: relative;
                            flex: 0 0 25%;
                            .mega-title{
                                position: relative;
                                font-size: 14px;
                                font-weight: 500;
                                padding-left: 45px;
                                ${({theme:t})=>t.rtl?"padding-right":"padding-left"}: 45px;
                                color: ${({theme:t})=>t["dark-color"]};
                                &:after{
                                    position: absolute;
                                    height: 5px;
                                    width: 5px;
                                    border-radius: 50%;
                                    ${({theme:t})=>t.rtl?"right":"left"}: 30px;
                                    top: 50%;
                                    transform: translateY(-50%);
                                    background-color: #C6D0DC;
                                    content: '';
                                }
                            }
                        }
                    }
                    ul{
                        li{
                            position: relative;
                            &:hover{
                                >a{
                                    padding-left: 45px;
                                }
                                &:after{
                                    opacity: 1;
                                    visibility: visible;
                                }
                            }
                            >a{
                                line-height: 3;
                                color: #868EAE;
                                font-weight: 400;
                                transition: .3s;
                            }

                            &:after{
                                width: 6px;
                                height: 1px;
                                border-radius: 50%;
                                position: absolute;
                                ${({theme:t})=>t.rtl?"right":"left"}: 30px;
                                top: 50%;
                                transform: translateY(-50%);
                                background-color: ${({theme:t})=>t["light-color"]};
                                content: '';
                                transition: .3s;
                                opacity: 0;
                                visibility: hidden;
                            }
                        }
                    }
                }
            }
        }
    }
`,h=a.ZP.aside`
  width: 100%;
  height: 100vh;
  position: relative;
  background-image: url("${i(74211)}");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: left top;
  @media only screen and (max-width: 767px){
    height: 100%;
  }
  .topShape {
    position: absolute;
    top: 0;
    right: 0;
    width: 400px;
  }
  .bottomShape {
    position: absolute;
    bottom: 0;
    left: 0;
    /* width: 400px; */
  }
  .auth-side-content{
    @media only screen and (max-width: 991px){
      h1{
        font-size: 20px;
      }
    }
    @media only screen and (max-width: 767px){
      h1{
        font-size: 24px;
        margin-bottom: 28px;
      }
    }
  }
`,x=a.ZP.div`
    padding: 100px;
    @media only screen and (max-width: 1599px){
      padding: 50px;
    }
    @media only screen and (max-width: 991px){
      padding: 20px;
    }
    @media only screen and (max-width: 767px){
      text-align: center;
    }
    .auth-content-figure{
      @media only screen and (max-width: 1199px){
        max-width: 420px;
      }
      @media only screen and (max-width: 991px){
        max-width: 100%;
      }
    }
`},51519:function(t,e,i){t.exports=i.p+"img/Logo_direa.06fa8eae.png"},74211:function(t){t.exports="data:image/png;base64,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"}}]);
//# sourceMappingURL=10.bdfc630f.js.map