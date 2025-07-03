import {createRouter, createWebHistory} from "vue-router";
import MainPage from "./components/MainPage.vue";
import SignUp from "./components/SignUp.vue";
import Faq from "./components/Faq.vue";
import Contacts from "./components/Contacts.vue";
import Support from "./components/Support.vue";
import CrudTable from "./components/Donation.vue";
import Donors from "./components/Donors.vue";
import Donation from "@/components/Donation.vue";
import Sponsor from "@/components/Sponsor.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            component: MainPage,
        },
        {
            path: '/sponsor',
            component: Sponsor
        },
        {
            path: '/faq',
            component: Faq
        },
        {
            path: '/contacts',
            component: Contacts
        },
        {
            path: '/support',
            component: Support },
        {
            path: '/donation',
            name: 'Donation',
            component: Donation },
        {
            path: '/donor',
            name: 'Donors',
            component: Donors }
    ]
})
export default router