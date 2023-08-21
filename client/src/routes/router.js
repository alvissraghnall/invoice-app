import Home from './Home.svelte';
import InvoiceItem from './InvoiceItem.svelte';

/** @type {import('svelte-router-spa').Route[]} */
export const routes = [
    {
        name: "/",
        component: Home,
        
    },
    {
        name: "invoice/:invoiceId",
        component: InvoiceItem
    }
];