<template>
  <AuthWrapper>
    <div class="auth-contents">
      <a-form @finish="handleSubmit" :model="formState" layout="vertical">
        <sdHeading as="h3">Forgot Password?</sdHeading>
        <p class="forgot-text">
          Enter the email address you used when you joined and we’ll send you instructions to reset your password.
        </p>
        <a-form-item label="Email Address" name="email">
          <a-input type="email" v-model:value="formState.email" placeholder="name@example.com" />
        </a-form-item>
        <a-form-item>
          <sdButton class="btn-reset" htmlType="submit" type="primary" size="large"> Send Reset Instructions </sdButton>
        </a-form-item>
        <p class="return-text">Return to <router-link to="/auth/login">Sign In</router-link></p>
      </a-form>
    </div>
  </AuthWrapper>
</template>
<script>
import { reactive, defineComponent } from 'vue';
import { AuthWrapper } from './style';

const ForgotPassword = defineComponent({
  name: 'ForgotPassword',
  components: { AuthWrapper },
  setup() {
    const handleSubmit = (values) => {
      console.log(values);
    };

    const formState = reactive({
      email: '',
    });

    return {
      handleSubmit,
      formState,
    };
  },
});

export default ForgotPassword;
</script>
