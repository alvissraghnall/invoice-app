/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { InvoiceDTO } from '../models/InvoiceDTO';

import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';

export class GetService {

    /**
     * Gets an invoice
     * Gets an invoice by specified ID provided in path as variable.
     * @param id 
     * @returns InvoiceDTO Gets an invoice
     * @throws ApiError
     */
    public static getInvoice(
id: string,
): CancelablePromise<InvoiceDTO> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/invoice/{id}',
            path: {
                'id': id,
            },
            errors: {
                400: `Bad Request`,
                404: `Not Found`,
                405: `Method Not Allowed`,
                415: `Unsupported Media Type`,
                422: `Unprocessable Entity`,
            },
        });
    }

    /**
     * Gets all invoices
     * Gets all invoices.
     * @returns InvoiceDTO Gets all invoices
     * @throws ApiError
     */
    public static getAllInvoices(): CancelablePromise<InvoiceDTO> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/invoice',
            errors: {
                400: `Bad Request`,
                404: `Not Found`,
                405: `Method Not Allowed`,
                415: `Unsupported Media Type`,
                422: `Unprocessable Entity`,
            },
        });
    }

}
