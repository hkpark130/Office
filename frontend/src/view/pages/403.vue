<template>
    <div>
        <Main>
            <ErrorWrapper>
                <img
                    :src="require(`../../static/img/pages/404.svg`)"
                    alt="403"
                />
                <sdHeading class="error-text" as="h3"> 403 </sdHeading>
                <p>권한 없음</p>
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
export default defineComponent({
    name: '403',
    components: {
        Main,
        ErrorWrapper,
    },
    setup() {
        return {};
    },
});
</script>
