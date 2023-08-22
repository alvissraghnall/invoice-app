import { writable, readable } from "svelte/store";
import { InvoiceService } from "../generated";

export const invoiceModalOpen = writable(false);
export const closeModalOpen = writable(false);

export const invoicesLoading = writable(true);

const getInvoices = async () => InvoiceService.getAllInvoices()
    .then(res => {
        console.log(res);
        return res;
    })
    .catch((err) => {
        console.error(err);
        return [];
    }).finally(() => {
        invoicesLoading.set(false);
    });

export const invoices = readable(await getInvoices());

export const editInvoice = writable(false);

export const currentInvoice = writable({});

