import app from './config/configApp';
import router from './routes/protectedRoute';
import store from '@/vuex/store';
import './static/css/style.css';
// Vue 3rd party plugins
import '@/core/plugins/ant-design';
import '@/core/plugins/fonts';
import '@/core/components/custom';
import '@/core/components/style';
import keycloak from '@/vuex/modules/authentication/keycloak/keycloak';

keycloak.init({ 
        onLoad: 'login-required',
}).then((authenticated) => {
    if (authenticated) {
        app.use(keycloak);
        // console.log(keycloak.loadUserProfile());
        console.log(keycloak.idTokenParsed);
        
    }
});

app.config.productionTip = true;
app.use(store);
app.use(router);
app.mount('#app');
