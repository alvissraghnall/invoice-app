/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */

import type { InvoiceItemDTO } from './InvoiceItemDTO';

export type InvoiceDTO = {
    id?: string;
    billerStreetAddress?: string;
    billerCity?: string;
    billerZipCode?: number;
    billerCountry?: string;
    clientName?: string;
    clientStreetAddress?: string;
    clientEmail?: string;
    clientCity?: string;
    clientZipCode?: number;
    clientCountry?: string;
    invoiceDate?: string;
    paymentTerms?: number;
    paymentDueDate?: string;
    productDesc?: string;
    status?: string;
    invoiceItemList: Array<InvoiceItemDTO>;
};
