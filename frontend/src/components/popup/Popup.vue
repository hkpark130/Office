<template>
    <a-popover
        class="className"
        v-model="visible"
        :placement="placement"
        :title="title && title"
        :trigger="action"
    >
        <template v-if="title" v-slot:title>
            <Title>{{ title }}</Title>
        </template>
        <template v-slot:content>
            <Content>
                <slot name="content">
                    <a to="#">
                        <sdFeatherIcons type="check" size="16" />
                        <span>Btn Dropdown one</span>
                    </a>
                    <a to="#">
                        <sdFeatherIcons type="check" size="16" />
                        <span>Btn Dropdown two</span>
                    </a>
                    <a to="#">
                        <sdFeatherIcons type="check" size="16" />
                        <span>Btn Dropdown three</span>
                    </a>
                </slot>
            </Content>
        </template>
        <slot></slot>
    </a-popover>
</template>

<script>
import { Content, Title } from './style';
import VueTypes from 'vue-types';
import { defineComponent, ref } from 'vue';

export default defineComponent({
    name: 'Popover',
    props: {
        placement: VueTypes.string.def('bottom'),
        title: VueTypes.string,
        class: VueTypes.string,
        action: VueTypes.string.def('hover'),
    },
    setup(prop) {
        const visible = false;
        const className = ref(prop.class);
        return {
            visible,
            className,
        };
    },
    components: {
        Content,
        Title,
    },
});
</script>
<style>
@import './style.css';
</style>
