<script>
    import { Navigate } from "svelte-router-spa";
    import { currentInvoice, editInvoice, invoices } from "../store";
    import { ChevronLeft, Icon } from "svelte-hero-icons";
    import { Button, InvoiceStatus } from "../components/shared";
    import { onMount } from "svelte";
    export let currentRoute;

    let invoiceId = currentRoute.namedParams.namedParams;

    let invoice = $invoices.find(
        inv => inv.id === invoiceId
    );

    let invoiceTotal = invoice.invoiceItemList.reduce(
        (acc, curr) => acc += curr.total, 0
    );

    const toggleEditInvoice = () => {
        editInvoice.update(v => !v);
    }

    const deleteInvoice = () => {

    }

    const updateStatus = () => {

    }

    onMount(() => {
        currentInvoice.set(invoice);
    });
</script>

{#if invoice}
    <div class="container">
        <Navigate to="/" styles="flex mb-8 items-center text-white text-sm">
            <span class="w-2 h-2 mr-4"><Icon src={ChevronLeft}  /></span>
            <span class="capitalize">go back</span>
        </Navigate>

        <div class="flex text-xs rounded-3xl bg-regColor items-center py-6 px-8">
            <div class="flex items-center">
                <span class="text-[#dfe3fa] mr-4">Status</span>
                <InvoiceStatus
                    status={invoice.status}
                />
            </div>

            <div class="flex flex-1 justify-end">
                <Button
                    class="bg-purple-900 text-white"
                    on:click={toggleEditInvoice}
                />

                <Button
                    class="bg-red-700 text-white"
                    on:click={deleteInvoice}
                />
                
                {#if invoice.status !== 'COMPLETED'}
                    <Button
                        class="bg-green-700 text-white"
                        on:click={updateStatus}>
                        Mark as Completed
                    </Button>
                {/if}
                
            </div>
        </div>

        <div class="flex flex-col rounded-3xl bg-regColor p-12 mt-6">
            <div class="flex">
                <div class="text-[#dfe3fa] flex-1 flex text-xs flex-col">
                    <p class="text-2xl uppercase text-white mb-2"><span class="text-[#888eb0]">#</span> {invoice.id} </p>
                    <p class="text-base"> { invoice.productDesc } </p>
                </div>

                <div class="text-[#dfe3fa] flex-1 flex flex-col text-xs items-end">
                    <p> {invoice.billerStreetAddress} </p>
                    <p> {invoice.billerCity} </p>
                    <p> { invoice.billerZipCode } </p>
                    <p> { invoice.billerCountry } </p>
                </div>
            </div>

            <div class="mt-12 text-[#dfe3fa] gap-4 flex">
                <div class="flex-1 flex flex-col">
                    <h4 class="text-xs font-normal mb-3">Invoice Date</h4>

                    <p class="text-base font-semibold"> {invoice.invoiceDate} </p>

                    <h4 class="text-xs font-normal mb-3 mt-8"> Payment Date </h4>

                    <p class="text-base font-semibold"> { invoice.paymentDueDate } </p>

                </div>

                <div class="flex-1 flex-col flex">
                    <h4 class="text-xs font-normal mb-3">Bill To </h4>
                    
                    <p class="text-base"> {invoice.clientName} </p>
                    <p class="text-xs mt-auto"> {invoice.clientStreetAddress} </p>
                    <p class="text-xs"> {invoice.clientCity} </p>
                    <p class="text-xs"> { invoice.clientZipCode } </p>
                    <p class="text-xs"> { invoice.clientCountry } </p>
                
                </div>

                <div class="flex-[2] flex flex-col">
                    <h4 class="text-xs font-normal mb-3">Sent To</h4>
                    <p class="text-base"> {invoice.clientEmail} </p>

                </div>
            </div>

            <div class="mt-12 flex-col flex">
                <div class="p-8 rounded-t-2xl bg-[#252945]">
                    <div class="text-[#dfe3fa] text-xs mb-8 flex">
                        <p class="flex-[3] text-left">Item Name</p>
                        <p class="flex-1 text-right">Qty.</p>
                        <p class="flex-1 text-right">Price</p>
                        <p class="flex-1 text-right">Total</p>
                    </div>

                    {#each invoice.invoiceItemList as item}
                        <div class="flex mb-8 text-sm text-white last:mb-0">

                            <p class="flex-[3] text-left">{item.itemName}</p>
                            <p class="flex-1 text-right">{item.qty}</p>
                            <p class="flex-1 text-right">{item.price}</p>
                            <p class="flex-1 text-right">{item.qty * item.price}</p>
                        </div>
                    {/each}
                </div>

                <div class="text-white p-8 bg-[rgba(12,14,22,.7)] items-center rounded-b-2xl flex">
                    <p class="flex-1 text-xs">Amount Due</p>
                    <p class="flex-1 text-lg text-right">${invoiceTotal}</p>
                </div>
            </div>
        </div>
    </div>
{/if}