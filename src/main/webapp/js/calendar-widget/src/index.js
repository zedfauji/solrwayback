/**
 * This is the main vue component for the graph.
 */

import Vue from 'vue'
import VueResource from 'vue-resource'

import {groupHarvestDatesByYearAndMonth} from './transformer';
import {calculateLinearActivityLevel, calculateLogarithmicActivityLevel} from './activity-level'
import VTooltip from 'v-tooltip'

Vue.use(VueResource);
Vue.use(VTooltip);

Vue.filter('human-date', function (value) {
    const months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

    if (value instanceof Date) {
        return `${months[value.getMonth()]} ${value.getDay()}, ${value.getFullYear()}`;
    } 
    
    return value;
});

Vue.filter('formatted-number', function (value) {
    if (!isNaN(value)) {
        return value.toLocaleString();
    }

    return value;
});

Vue.component('harvest-title', {
    props: ['url'],
    template: `<h1>Harvests for {{ url }}</h1>`
});

Vue.component('harvest-date', {
    props: ['url'],
    data: () => {
        return {
            harvestData: null,
        }
    },
    template: `
        <div v-if="harvestData" class="tableContainer">
            <p>
                First harvest: {{ harvestData.fromDate | human-date }}<br>
                Latest harvest: {{ harvestData.toDate | human-date }}
            </p>
            <p>Total harvests: {{ harvestData.numberOfHarvests | formatted-number }}</p>
            <table>
                <tr><td>&nbsp;</td></tr>
                <tr><td>January</td></tr>
                <tr><td>February</td></tr>
                <tr><td>March</td></tr>
                <tr><td>April</td></tr>
                <tr><td>May</td></tr>
                <tr><td>June</td></tr>
                <tr><td>July</td></tr>
                <tr><td>August</td></tr>
                <tr><td>September</td></tr>
                <tr><td>October</td></tr>
                <tr><td>November</td></tr>
                <tr><td>December</td></tr>
            </table>
            <table v-for="(months, year) in harvestData.dates">
                <thead>
                    <tr>
                        <th>{{ year }}</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(data, month) in months">
                        <td v-tooltip.top-center="'Harvests: ' + data.numberOfHarvests.toLocaleString()" v-bind:class="{activityLevel4: data.activityLevel === 4, activityLevel3: data.activityLevel === 3, activityLevel2: data.activityLevel === 2, activityLevel1: data.activityLevel === 1}">&nbsp;</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div v-else>
            <p>Fetching harvests</p>
        </div>
    `,
    created() {
        this.$http.get("/solrwayback/services/harvestDates?url=" + encodeURIComponent(this.url))
        .then(response => {
            this.harvestData = groupHarvestDatesByYearAndMonth(response.data.dates, calculateLinearActivityLevel);
        });
    }
});


let app = new Vue({
    el: "#app",
    data: {
        url: window.solrWaybackConfig.url
    }
});