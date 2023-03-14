
<div class="text-white/90 w-full max-w-[53rem] py-10 px-2.5 my-0 mx-auto lg:pt-20">
    <div class="mb-16 flex">
        <div class="flex flex-col flex-1">
            <h1 class="font-bold text-2xl">Invoices</h1>
            <span class="text-sm mt-1">There are {invoiceNumber} total invoices </span>
        </div>
        <div class="flex flex-1 justify-end items-center">
            <button on:click={toggleFilterMenu} class="filter relative mr-10 flex outline-none focus:outline-none border-none items-center cursor-pointer">
                <span class="text-xs ">Filter by status</span>
                <span class="ml-3 w-4 h-4"><Icon src={ArrowDown} /></span>
                {#if filterMenu}
                    <ul class="filter-menu w-28 absolute top-6 list-none bg-regColor shadow-md">
                        {#each filterListItems as item}
                            <li class="capitalize cursor-pointer text-xs py-2.5 px-5 hover:text-regColor hover:bg-white bg-regColor text-white">{item}</li>
                        {/each}
                    </ul>
                {/if}
            </button>
            <button type="button" class="cursor-pointer py-2 px-2.5 bg-violet-600 rounded-[2rem] text-xs mr-2 text-white outline-none flex items-center" on:click={newInvoice}>
                <div class="mr-1 place-content-center w-5 h-5 flex">
                    <Icon src={PlusCircle} />
                </div>
                <span class="text-xs">New Invoice</span>
            </button>
        </div>
    </div>
</div>


<script>
    import {Icon, ArrowDown, PlusCircle} from "svelte-hero-icons";
    let invoiceNumber = 6;
    let filterMenu = false;
    const newInvoice = ev => {
        
    }

    const toggleFilterMenu = () => filterMenu = !filterMenu;

    const filterListItems = [
        "draft",
        "clear filter",
        "paid",
        "pending"
    ]
</script>

