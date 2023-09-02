<script>
    import { Navigate, navigateTo } from "svelte-router-spa";
    import { currentInvoice, editInvoice, invoiceModalOpen, invoices, invoicesLoading } from "../store";
    import { ChevronLeft, Icon, Pencil, Trash } from "svelte-hero-icons";
    import { onMount } from "svelte";
    import { InvoiceService } from "../generated";
    import { Meta, Button, InvoiceStatus, NotFound } from "../components";
    import toast from "svelte-french-toast";
    export let currentRoute;

    let invoiceId = currentRoute.namedParams.invoiceId;

    let invoice;
    
    $: invoiceTotal = invoice?.invoiceItemList?.reduce(
        (acc, curr) => acc += (curr.qty * curr.price), 0
    ) ?? 0;

    const toggleEditInvoice = () => {
        editInvoice.update(v => !v);
        invoiceModalOpen.set(true);
        console.log('wk');

        console.log($invoiceModalOpen, $editInvoice);
    }

    const deleteInvoice = async () => {
        InvoiceService.removeById(
            $currentInvoice.id
        ).then(() => {
            toast.success(
                'Invoice deleted!', {
                    style: 'background-color: #333; color: #fff; border-radius: 120px;'
                }
            );
            navigateTo('/');
        }).catch(err => {
            toast.error(
                err?.body?.message, {
                    style: 'border-radius: 120px;'
                }
            );
        });
        
    }

    const updateStatus = async () => {
        const inv = $currentInvoice;
        inv.status = 'COMPLETED';
        await InvoiceService.replaceById(
            invoiceId,
            inv
        ).then(() => {
            invoice.status = inv.status;
            toast.success("Successfully marked as Completed!", {
                icon: '👏',
                style: 'border-radius: 120px; color: #fff; background-color: #4caf50;'
            });
        }).catch(err => {
            toast.error(err?.body?.message, {
                className: 'bg-butCol'
            });
        });
    }

    //:: Start Responsive design immediately !!!!!!!!

    invoicesLoading.set(true);

    const fetchInvoice = async (id) => {
        const getInvoice = await InvoiceService.getInvoice(
            id
        ).then(res => {
                console.log(res);
                return res;
            })
        .catch((err) => {
            console.error(err);
            return {};
        })
        .finally(() => {
            invoicesLoading.set(false);
        });

        return getInvoice;
    }

    onMount(async () => {
        console.log("men mnt");
        
        console.log(currentRoute);

        invoice = await fetchInvoice(invoiceId);

        console.log(invoice);

        currentInvoice.set(invoice);
        console.log($invoices, invoice, $currentInvoice);
    });
</script>

<Meta title="Invoice {$currentInvoice.id}" keywords="invoice, frontendmentor, invoice {$currentInvoice.id}, invoice app, Alviss Raghnall" />


{#if Object.keys($currentInvoice).length !== 0}
    <div class="m-6 p-4 mx-auto w-full max-md:mx-auto max-md:m-3 lg:max-w-3xl max-sm:p-0">
        <Navigate to="/" styles="flex mb-8 w-max items-center dark:text-white text-[#0c0e16] text-sm ml-2">
            <span class="w-3 h-3 inline-block mr-2 text-base font-semibold"><Icon src={ChevronLeft}  /></span>
            <span class="capitalize">go back</span>
        </Navigate>

        <div class="flex text-xs rounded-3xl bg-mainBg dark:bg-regColor items-center py-6 px-8 max-md:px-3 shadow-lg dark:shadow-none">
            <div class="flex items-center max-md:mr-[0.2rem]">
                <span class="dark:text-[#dfe3fa] text-gray-700 mr-4 max-md:hidden">Status</span>
                <InvoiceStatus
                    status={$currentInvoice.status}
                />
            </div>

            <div class="flex flex-1 justify-end gap-1">
                <Button
                    class="bg-purple-900 text-white !py-2 !px-2 !mr-0 font-semibold flex justify-center items-center"
                    on:click={toggleEditInvoice}
                >
                    <span class="w-6 h-6 inline-block font-semibold max-md:h-5 max-md:w-5">
                        <Icon src={Pencil} />
                    </span>
                </Button>

                <Button
                    class="bg-red-700 text-white !py-2 !px-2 !mr-0 flex justify-center items-center"
                    on:click={deleteInvoice}
                >
                    <span class="md:w-6 md:h-6 h-5 w-5 inline-block font-semibold">
                        <Icon src={Trash} />
                    </span>
                </Button>
                
                {#if $currentInvoice.status !== 'COMPLETED'}
                    <Button
                        class="bg-green-700 text-white max-md:text-sm"
                        on:click={updateStatus}>
                        Mark as Completed
                    </Button>
                {/if}
                
            </div>
        </div>

        <div class="flex flex-col rounded-3xl bg-mainBg dark:bg-regColor p-12 max-md:p-6 max-sm:p-5 max-[420px]:p-4 mt-6 shadow-lg dark:shadow-none">
            <div class="flex max-md:flex-col">
                <div class="dark:text-[#dfe3fa] text-stone-800 flex-[2] flex text-xs flex-col">
                    <p class="text-2xl uppercase dark:text-white mb-2 text-black"><span class="text-[#888eb0]">#</span>{$currentInvoice.id}</p>
                    <p class="text-base text-slate-700 dark:text-white"> { $currentInvoice.productDesc ?? '' } </p>
                </div>

                <div class="dark:text-[#dfe3fa] text-stone-800 flex-1 flex flex-col text-xs items-end capitalize mt-2 gap-1 max-md:items-start">
                    <p> {$currentInvoice.billerStreetAddress ?? ''} </p>
                    <p> {$currentInvoice.billerCity ?? ''} </p>
                    <p> { $currentInvoice.billerZipCode  ?? 0 } </p>
                    <p> { $currentInvoice.billerCountry  ?? '' } </p>
                </div>
            </div>

            <div class="mt-12 dark:text-[#dfe3fa] text-stone-800 gap-4 flex">
                <div class="flex-1 flex flex-col">
                    <h4 class="text-xs font-normal mb-3">Invoice Date</h4>

                    <p class="text-base font-semibold"> 
                        {($currentInvoice.invoiceDate ? new Date($currentInvoice.invoiceDate) : new Date()).toLocaleDateString("en-us", {
                            year: "numeric", month: "short",
                            day: "numeric"
                        })}    
                    </p>

                    <h4 class="text-xs font-normal mb-3 mt-8"> Payment Date </h4>

                    <p class="text-base font-semibold"> 
                        {$currentInvoice.paymentDueDate ? new Date($currentInvoice.paymentDueDate).toLocaleDateString("en-us", {
                            year: "numeric", month: "short",
                            day: "numeric"
                        }) : ''} 
                    </p>

                </div>

                <div class="flex-1 flex-col flex">
                    <h4 class="text-xs font-normal mb-3">Bill To </h4>
                    
                    <p class="text-base"> {$currentInvoice.clientName  ?? ''} </p>
                    <p class="text-xs mt-auto"> {$currentInvoice.clientStreetAddress ?? ''} </p>
                    <p class="text-xs"> {$currentInvoice.clientCity  ?? ''} </p>
                    <p class="text-xs"> { $currentInvoice.clientZipCode ?? ''} </p>
                    <p class="text-xs"> { $currentInvoice.clientCountry ?? ''} </p>
                
                </div>

                <div class="flex-[2] flex flex-col">
                    <h4 class="text-xs font-normal mb-3">Sent To</h4>
                    <p class="text-base"> {$currentInvoice.clientEmail  ?? ''} </p>

                </div>
            </div>

            <div class="mt-12 flex-col flex">
                <div class="p-8 rounded-t-2xl dark:bg-butCol bg-[#f9fafe]">
                    <div class="text-slate-800 dark:text-[#dfe3fa] text-xs mb-8 flex">
                        <p class="flex-[3] text-left">Item Name</p>
                        <p class="flex-1 text-right">Qty.</p>
                        <p class="flex-1 text-right">Price</p>
                        <p class="flex-1 text-right text-[#0c0e16] dark:text-white">Total</p>
                    </div>

                    {#each $currentInvoice.invoiceItemList as item}
                        <div class="flex mb-8 text-sm text-slate-800 dark:text-white last:mb-0">

                            <p class="flex-[3] text-left">{item.itemName}</p>
                            <p class="flex-1 text-right">{item.qty}</p>
                            <p class="flex-1 text-right">{item.price}</p>
                            <p class="flex-1 text-right">{item.qty * item.price}</p>
                        </div>
                    {/each}
                </div>

                <div class="text-white p-8 bg-black dark:bg-[rgba(12,14,22,.7)] items-center rounded-b-2xl flex">
                    <p class="flex-1 text-xs">Amount Due</p>
                    <p class="flex-1 text-lg text-right">$ {invoiceTotal}</p>
                </div>
            </div>
        </div>
    </div>
    {:else}
        <NotFound {currentRoute} />
{/if}



<style lang="postcss">
    @media screen and (max-width: 768px) {
        :global(main) {
        
            padding: 0 !important;
            max-width: 100%;
        }
    }
</style>