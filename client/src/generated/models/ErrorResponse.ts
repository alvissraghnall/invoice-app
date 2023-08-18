/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */

import type { FieldError } from './FieldError';

export type ErrorResponse = {
    httpStatus?: number;
    exception?: string;
    message?: string;
    fieldErrors?: Array<FieldError>;
};
