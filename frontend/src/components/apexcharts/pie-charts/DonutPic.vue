<template>
    <div id="chart">
        <apexchart
            type="donut"
            :options="chartOptions"
            :series="series"
        ></apexchart>
    </div>
</template>

<script>
import { defineComponent } from 'vue';
export default defineComponent({
    name: 'DonutPic',
    components: {},
    setup() {
        const series = [44, 55, 41, 17, 15];
        const chartOptions = {
            chart: {
                type: 'donut',
            },
            responsive: [
                {
                    breakpoint: 480,
                    options: {
                        chart: {
                            width: 200,
                        },
                        legend: {
                            position: 'bottom',
                        },
                    },
                },
            ],
        };
        return {
            series,
            chartOptions,
        };
    },
});
</script>
