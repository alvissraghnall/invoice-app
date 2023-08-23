<script>
    import { Navigate } from "svelte-router-spa";
    import { currentInvoice, editInvoice, invoices, invoicesLoading } from "../store";
    import { ChevronLeft, Icon, Pencil, Trash } from "svelte-hero-icons";
    import { Button, InvoiceStatus } from "../components/shared";
    import { onMount } from "svelte";
    import { InvoiceService } from "../generated";
    import { InvoicesLoading } from "../components";
    import toast from "svelte-french-toast";
    export let currentRoute;

    let invoiceId = currentRoute.namedParams.invoiceId;

    let invoice, invoiceTotal; 

    const toggleEditInvoice = () => {
        editInvoice.update(v => !v);
    }

    const deleteInvoice = () => {

    }

    const updateStatus = async () => {
        const inv = $currentInvoice;
        inv.status = 'COMPLETED';
        await InvoiceService.replaceById(
            invoiceId,
            inv
        ).then(() => {
            
            toast.success("Successfully marked as Completed!");
        });
    }

    invoicesLoading.set(true);

    const fetchInvoices = async () => {
        const getInvoices = await InvoiceService.getAllInvoices()
            .then(res => {
                console.log(res);
                return res;
            })
            .catch((err) => {
                console.error(err);
                return [];
            })
            .finally(() => {
                invoicesLoading.set(false);
            });

        invoices.set(getInvoices);
    }

    onMount(async () => {
        console.log("men mnt");
        await fetchInvoices();
        console.log(currentRoute);

        invoice = $invoices.find(
            inv => inv.id === invoiceId
        );

        invoiceTotal = invoice?.invoiceItemList?.reduce(
            (acc, curr) => acc += (curr.qty * curr.price), 0
        ) ?? 0;

        currentInvoice.set(invoice);
        console.log($invoices, invoice, $currentInvoice);
    });
</script>

{#if $invoicesLoading}
    <div class="bg-butCol/80 w-full min-h-screen flex justify-center items-center">
      <div class="bg-butCol/50 p-10 shadow-md rounded-xl relative">
        <InvoicesLoading />
      </div>
    </div>
  {:else}
    {#if invoice}
        <div class="m-6 p-4 mx-auto w-full max-md:mx-auto max-md:m-3 lg:max-w-3xl">
            <Navigate to="/" styles="flex mb-8 items-center text-white text-sm ml-2">
                <span class="w-3 h-3 inline-block mr-2 text-base font-semibold"><Icon src={ChevronLeft}  /></span>
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
                        class="bg-purple-900 text-white !py-2 !px-2 font-semibold flex justify-center items-center"
                        on:click={toggleEditInvoice}
                    >
                        <span class="w-6 h-6 inline-block font-semibold">
                            <Icon src={Pencil} />
                        </span>
                    </Button>

                    <Button
                        class="bg-red-700 text-white !py-2 !px-2 flex justify-center items-center"
                        on:click={deleteInvoice}
                    >
                        <span class="w-6 h-6 inline-block font-semibold">
                            <Icon src={Trash} />
                        </span>
                    </Button>
                    
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
                        <p class="text-base"> { invoice.productDesc ?? '' } </p>
                    </div>

                    <div class="text-[#dfe3fa] flex-1 flex flex-col text-xs items-end">
                        <p> {invoice.billerStreetAddress ?? ''} </p>
                        <p> {invoice.billerCity} </p>
                        <p> { invoice.billerZipCode } </p>
                        <p> { invoice.billerCountry } </p>
                    </div>
                </div>

                <div class="mt-12 text-[#dfe3fa] gap-4 flex">
                    <div class="flex-1 flex flex-col">
                        <h4 class="text-xs font-normal mb-3">Invoice Date</h4>

                        <p class="text-base font-semibold"> 
                            {invoice.invoiceDate ? new Date(invoice.invoiceDate).toLocaleDateString("en-us", {
                                year: "numeric", month: "short",
                                day: "numeric"
                            }) : ''}    
                        </p>

                        <h4 class="text-xs font-normal mb-3 mt-8"> Payment Date </h4>

                        <p class="text-base font-semibold"> 
                            {invoice.paymentDueDate ? new Date(invoice.paymentDueDate).toLocaleDateString("en-us", {
                                year: "numeric", month: "short",
                                day: "numeric"
                            }) : ''} 
                        </p>

                    </div>

                    <div class="flex-1 flex-col flex">
                        <h4 class="text-xs font-normal mb-3">Bill To </h4>
                        
                        <p class="text-base"> {invoice.clientName} </p>
                        <p class="text-xs mt-auto"> {invoice.clientStreetAddress ?? ''} </p>
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
                        <p class="flex-1 text-lg text-right">$ {invoiceTotal}</p>
                    </div>
                </div>
            </div>
        </div>
    {/if}
{/if}
