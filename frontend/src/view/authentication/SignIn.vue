<template>
  <AuthWrapper>
    <p class="auth-notice">
      신규 가입은 문의해주세요.
      <!-- <router-link to="/auth/register">Sign up now</router-link> -->
    </p>
    <div class="auth-contents">
      <a-form @finish="handleSubmit" :model="formState" layout="vertical">
        <a-form-item name="username" label="Email Address">
          <a-input type="email" v-model:value="formState.email" />
        </a-form-item>
        <a-form-item name="password" initialValue="123456" label="Password">
          <a-input
            type="password"
            v-model:value="formState.password"
            placeholder="Password"
          />
        </a-form-item>
        <div class="auth-form-action">
          <router-link class="forgot-pass-link" to="/auth/forgotPassword">
            Forgot password?
          </router-link>
        </div>
        <a-form-item>
          <sdButton
            class="btn-signin"
            htmlType="submit"
            type="primary"
            size="large"
          >
            {{ isLoading ? "Loading..." : "Sign In With SSO" }}
          </sdButton>
        </a-form-item>
      </a-form>
    </div>
  </AuthWrapper>
</template>
<script>
import { computed, reactive, ref, defineComponent } from 'vue';
import { useStore } from 'vuex';
import { AuthWrapper } from './style';
import { useRouter } from 'vue-router';

const SignIn = defineComponent({
  name: "SignIn",
  components: { AuthWrapper },
  setup() {
    const { state, dispatch } = useStore();
    const isLoading = computed(() => state.auth.loading);
    const checked = ref(null);
    const router = useRouter();
    const handleSubmit = () => {
      router.push("/");
      dispatch("login");
    };
/*
    const handleSubmit = value => {
      dispatch('keycloakLogin', { data: value });
    };
*/
    const onChange = (checked) => {
      checked.value = checked;
    };
    const formState = reactive({
      email: "example@email.com",
      password: "1234565",
    });
    return {
      isLoading,
      checked,
      handleSubmit,
      onChange,
      formState
    };
  },
});

export default SignIn;
</script>
