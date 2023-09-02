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
    <Navigate to="invoice/{invoice.id}" styles="flex max-lg:flex-col max-lg:gap-y-2 cursor-pointer gap-4 mb-4 dark:text-white text-gray-600 rounded-3xl no-underline py-7 px-8 bg-mainBg dark:bg-regColor items-center hover:border hover:border-solid hover:border-[#7c5dfa]">
        <div class="flex items-center basis-3/5 gap-4 max-lg:flex-col max-lg:gap-3 w-full max-lg:items-start">
            <span class="flex-1 uppercase text-sm text-neutral-600">
                #<span class="text-[#0c0e16] dark:text-white font-semibold">{invoice.id.slice(18, 24)}</span>
            </span>
            <span class="flex-1 text-sm text-gray-600 dark:text-white">
                {invoice.paymentDueDate ? new Date(invoice.paymentDueDate).toLocaleDateString("en-us", {
                    year: "numeric", month: "short",
                    day: "numeric"
                }) : ''}
            </span>
            <span class="flex-1 text-sm max-lg:hidden">
                {invoice.clientName}
            </span>
        </div>

        <div class="flex gap-4 basis-2/5 items-center w-full justify-between">
            <span class="flex-1 text-base font-semibold text-black dark:text-white">${totalAmount}</span>

            <div class="">
                <InvoiceStatus
                    status={invoice.status}
                />
            </div>

            <div class="ml-3 w-4 h-4 font-bold dark:text-white text-neutral-600 max-lg:hidden">  
                <Icon src={ChevronRight} />
            </div>

        </div>

    </Navigate>
</div>