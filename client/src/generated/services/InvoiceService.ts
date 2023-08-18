/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { InvoiceDTO } from '../models/InvoiceDTO';

import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';

export class InvoiceService {

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
     * Gets an invoice
     * Gets an invoice by specified ID provided in path as variable.
     * @param id 
     * @returns InvoiceDTO Gets an invoice
     * @throws ApiError
     */
    public static getInvoice1(
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
     * Updates an invoice
     * Updates an invoice via specified ID in path as variable, and parts of the entity to be updated, provided in response body.
     * @param id 
     * @param requestBody 
     * @returns void 
     * @throws ApiError
     */
    public static replaceById(
id: string,
requestBody: InvoiceDTO,
): CancelablePromise<void> {
        return __request(OpenAPI, {
            method: 'PUT',
            url: '/invoice/{id}',
            path: {
                'id': id,
            },
            body: requestBody,
            mediaType: 'application/json',
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
     * Updates an invoice
     * Updates an invoice via specified ID in path as variable, and parts of the entity to be updated, provided in response body.
     * @param id 
     * @param requestBody 
     * @returns void 
     * @throws ApiError
     */
    public static replaceById1(
id: string,
requestBody: InvoiceDTO,
): CancelablePromise<void> {
        return __request(OpenAPI, {
            method: 'PUT',
            url: '/invoice/{id}',
            path: {
                'id': id,
            },
            body: requestBody,
            mediaType: 'application/json',
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

    /**
     * Gets all invoices
     * Gets all invoices.
     * @returns InvoiceDTO Gets all invoices
     * @throws ApiError
     */
    public static getAllInvoices1(): CancelablePromise<InvoiceDTO> {
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

    /**
     * Create new invoice
     * Create a new invoice by specifying required, and/or optional fields in request body.
     * @param requestBody 
     * @returns InvoiceDTO creates a new invoice
     * @throws ApiError
     */
    public static createInvoice(
requestBody: InvoiceDTO,
): CancelablePromise<InvoiceDTO> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/invoice',
            body: requestBody,
            mediaType: 'application/json',
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
     * Create new invoice
     * Create a new invoice by specifying required, and/or optional fields in request body.
     * @param requestBody 
     * @returns InvoiceDTO creates a new invoice
     * @throws ApiError
     */
    public static createInvoice1(
requestBody: InvoiceDTO,
): CancelablePromise<InvoiceDTO> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/invoice',
            body: requestBody,
            mediaType: 'application/json',
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
