
<div class="text-white/90 w-full max-w-[53rem] py-10 px-2.5 my-0 mx-auto lg:pt-20 font-poppins">
    <div class="mb-16 flex">
        <div class="flex flex-col flex-1">
            <h1 class="font-bold text-2xl">Invoices</h1>
            <span class="text-sm mt-1">
                <span class="hidden md:inline">There are </span>
                {$invoices.length} 
                <span class="hidden md:inline">total </span>
                invoices 
            </span>
        </div>
        <div class="flex flex-1 justify-end items-center">
            <button on:click={toggleFilterMenu} class="filter relative mr-10 flex outline-none focus:outline-none border-none items-center cursor-pointer">
                <span class="text-xs font-medium">
                    Filter
                    <span class="hidden md:inline"> by status</span>
                </span>
                <span class="ml-3 w-4 h-4">
                    <Icon src={ArrowDown} />
                </span>
                <!-- {#if filterMenu} -->
                <Dialog open={filterMenu}>
                    <ul class="filter-menu w-28 absolute top-6 list-none bg-regColor shadow-md rounded">
                        {#each filterListItems as item}
                            <li class="capitalize cursor-pointer text-sm font-medium py-2.5 px-5 hover:text-regColor hover:bg-white bg-regColor text-white">{item}</li>
                        {/each}
                    </ul>
                </Dialog>
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
        <div class="flex flex-col mt-20 items-center">
            <img class="w-52 h-48" src="assets/illustration-empty.svg" alt="" />
            <h3 class="text-2xl mt-10">There is nothing here!</h3>
            <p class="text-center text-sm max-w-xs font-light mt-4">Create a new invoice by clicking the "Add New Invoice" button and get started. </p>
        </div>
    {/if}
    
</div>


<script>
    import { invoices, invoicesLoading } from "../store";
    import { Invoice } from "../components";
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

