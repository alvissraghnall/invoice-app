import { writable, readable } from "svelte/store";

export const invoiceModalOpen = writable(false);
export const closeModalOpen = writable(false);

export const invoicesLoading = writable(true);

export const invoices = writable([]);

export const editInvoice = writable(false);

export const currentInvoice = writable({});

