/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { InvoiceDTO } from '../models/InvoiceDTO';

import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';

export class PostService {

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

}
