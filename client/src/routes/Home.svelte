<Meta title="Home" keywords="Invoicing, frontendmentor, invoice app, Alviss Raghnall" />
<div class="dark:text-white/90 text-neutral-800 w-full max-w-[53rem] py-10 px-2.5 my-0 mx-auto lg:pt-20 font-poppins">
    <div class="mb-16 flex">
        <div class="flex flex-col flex-1">
            <h1 class="font-bold text-2xl">Invoices</h1>
            <span class="text-sm mt-1 text-[#888eb0] dark:text-white">
                <span class="hidden md:inline">There are </span>
                {$invoices.length}
                <span class="hidden sm:inline">total</span>
                invoices
            </span>
        </div>
        <div class="flex flex-1 justify-end items-center">
            <button on:click={toggleFilterMenu} class="filter relative mr-10 flex outline-none focus:outline-none border-none items-center cursor-pointer">
                <span class="text-base font-medium">
                    Filter
                    <span class="hidden md:inline"> by status</span>
                </span>
                <span class="ml-3 w-4 h-4 relative">
                    <Icon src={ArrowDown} />

                    <Dialog open={filterMenu} class="bg-white dark:bg-butCol">
                        <ul class="filter-menu w-28 absolute top-6 list-none bg-regColor shadow-md rounded">
                            {#each filterListItems as item}
                                <li class="capitalize cursor-pointer text-sm font-medium py-2.5 px-5 hover:text-regColor hover:bg-white bg-regColor text-white">{item}</li>
                            {/each}
                        </ul>
                    </Dialog>
                </span>
                <!-- {#if filterMenu} -->
                
                <!-- {/if} -->
            </button>
            <button class="cursor-pointer py-2 px-2.5 bg-violet-600 rounded-[2rem] text-xs mr-2 text-white outline-none flex items-center" on:click={toggleInvoiceModal}>
                <div class="mr-1 place-content-center w-5 h-5 flex text-violet-600">
                    <Icon src={PlusCircle} class="bg-white rounded-full" />
                </div>
                <span class="text-xs">
                    New 
                    <span class="hidden md:inline">Invoice</span>
                </span>
            </button>
        </div>
    </div>

    <!-- invoices -->
    
    {#if $invoices}
        <!-- {#await $invoices then value} -->
            
            {#each $invoices as invoice, index (invoice.id)}
                <Invoice {invoice} />
            {/each}

            <!-- {:catch error} -->
                <!-- <span class="sr-only">No invoices</span> -->
        <!-- {/await} -->
    {:else}
        <NotFound />
    {/if}
    
</div>

<script>
    import { invoices, invoicesLoading } from "../store";
    import { Invoice, Meta, NotFound } from "../components";
    import { invoiceModalOpen } from "../store";
    import {Icon, ArrowDown, PlusCircle} from "svelte-hero-icons";
    import { onMount } from "svelte";
    import { InvoiceService } from "../generated";
    import { Dialog } from "@rgossiaux/svelte-headlessui";

    let filterMenu = false;
    // const newInvoice = ev => {
        
    // }

    const toggleInvoiceModal = () => {
        invoiceModalOpen.update(value => !value);
    }

    const toggleFilterMenu = () => filterMenu = !filterMenu;

    const filterListItems = [
        "draft",
        "clear filter",
        "paid",
        "pending"
    ];

    onMount(async () => {
        console.log("men mnt");
        const getInvoices = await InvoiceService.getAllInvoices()
            .then(res => {
                console.log(res);
                return res;
            })
            .catch((err) => {
                console.error(err);
                return [];
            }).finally(() => {
                invoicesLoading.set(false);
            });

        invoices.set(getInvoices);
    })
</script>

