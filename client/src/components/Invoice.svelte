<script>
    import {
        Navigate
    } from "svelte-router-spa";
    import {
        ArrowRight,
        Icon,
        ChevronRight
    } from "svelte-hero-icons";
    import {InvoiceStatus} from "./shared";

    export let invoice;

    const totalAmount = 0;
</script>

<div>
    <Navigate to="invoice/{invoice.id}" styles="flex cursor-pointer gap-4 mb-4 text-white rounded-3xl no-underline py-7 px-8 bg-regColor items-center">
        <div class="flex items-center basis-7/12 gap-4">
            <span class="track_no flex-1 uppercase text-sm">
                #{invoice.id.slice(16, 24)}
            </span>
            <span class="due_date flex-1 text-sm">
                {invoice.paymentDueDate ? new Date(invoice.paymentDueDate).toLocaleDateString("en-us", {
                    year: "numeric", month: "short",
                    day: "numeric"
                }) : ''}
            </span>
            <span class="person flex-1  text-sm">
                {invoice.clientName}
            </span>
        </div>

        <div class="flex gap-4 basis-2/5 items-center">
            <span class="flex-1 text-base font-semibold">${totalAmount}</span>

            <InvoiceStatus
                status={invoice.status}
            />

            <div class="ml-3 w-4 h-4 font-bold text-white">  
                <Icon src={ChevronRight} />
            </div>

        </div>

    </Navigate>
</div>