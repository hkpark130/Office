<template>
    <div>
        <Main>
            <ErrorWrapper>
                <img
                    :src="require(`../../static/img/pages/404.svg`)"
                    alt="500"
                />
                <sdHeading class="error-text" as="h3"> 500 </sdHeading>
                <p>{{ msg }}</p>
                <router-link to="/">
                    <sdButton size="default" type="primary" to="/">
                        Return Home
                    </sdButton>
                </router-link>
            </ErrorWrapper>
        </Main>
    </div>
</template>

<script>
import { Main } from '../styled';
import { ErrorWrapper } from './style';
import { defineComponent } from 'vue';
import { useRouter } from 'vue-router';

export default defineComponent({
    name: '500',
    components: {
        Main,
        ErrorWrapper,
    },
    setup() {
        const router = useRouter();
        const msg = router.currentRoute.value.query.msg;
        return {
            msg,
        };
    },
});
</script>
