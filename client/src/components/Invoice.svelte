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
        <div class="flex items-center basis-3/5 gap-4">
            <span class="flex-1 uppercase text-sm">
                #{invoice.id.slice(18, 24)}
            </span>
            <span class="flex-1 text-sm">
                {invoice.paymentDueDate ? new Date(invoice.paymentDueDate).toLocaleDateString("en-us", {
                    year: "numeric", month: "short",
                    day: "numeric"
                }) : ''}
            </span>
            <span class="flex-1 text-sm">
                {invoice.clientName}
            </span>
        </div>

        <div class="flex gap-4 basis-2/5 items-center">
            <span class="flex-1 text-base font-semibold">${totalAmount}</span>

            <div class="hidden min-[415px]:block">
                <InvoiceStatus
                    status={invoice.status}
                />
            </div>

            <div class="ml-3 w-4 h-4 font-bold text-white">  
                <Icon src={ChevronRight} />
            </div>

        </div>

    </Navigate>
</div>