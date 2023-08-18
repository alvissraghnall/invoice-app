/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { InvoiceDTO } from '../models/InvoiceDTO';

import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';

export class PutService {

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

}
