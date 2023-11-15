<template>
    <div id="chart">
        <apexchart
            type="radialBar"
            height="350"
            :options="chartOptions"
            :series="series"
        ></apexchart>
    </div>
</template>

<script>
import { defineComponent } from 'vue';
export default defineComponent({
    name: 'BasicPic',
    components: {},
    setup() {
        const series = [70];
        const chartOptions = {
            chart: {
                height: 350,
                type: 'radialBar',
            },
            plotOptions: {
                radialBar: {
                    hollow: {
                        size: '70%',
                    },
                },
            },
            labels: ['Cricket'],
        };
        return {
            series,
            chartOptions,
        };
    },
});
</script>
