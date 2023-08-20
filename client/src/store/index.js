import { writable, readable } from "svelte/store";
import { InvoiceService } from "../generated";

export const invoiceModalOpen = writable(false);
export const closeModalOpen = writable(false);

export const invoicesLoading = writable(true);

const getInvoices = InvoiceService.getAllInvoices()
    .catch((err) => {
        console.error(err);
        return [];
    }).finally(() => {
        invoicesLoading.set(false);
    });

export const invoices = readable(getInvoices);
