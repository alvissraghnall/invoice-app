<script>
    import { Menu, MenuButton, MenuItem, MenuItems, Transition } from "@rgossiaux/svelte-headlessui";
    import { createEventDispatcher } from "svelte";
    import { ArrowDown, Icon, ChevronDown, ChevronUp } from "svelte-hero-icons";

    export let filterListItems;
    export let currentFilter;

    const dispatch = createEventDispatcher();

    const filterInvoices = (item) => {
        console.log(item);
        dispatch('filter', item);
    }
</script>


<Menu as="div" class="relative inline-block text-left">
    <div>
        <MenuButton class="inline-flex items-center w-full justify-center rounded-md outline-none focus:outline-none border-none cursor-pointer focus-visible:ring-opacity-75 focus-visible:ring-white focus-visible:ring-2">
            <span class="text-base font-medium">
                Filter
                <span class="hidden md:inline"> by status: {!!currentFilter ? currentFilter : ''}</span>
            </span>
            <span class="ml-1 mr-2 w-5 h-5 relative text-black dark:text-violet-200" aria-hidden="true">
                <Icon src={ChevronDown} />
            </span>
        </MenuButton>
    </div>

    <Transition
        as="div"
        enter="transition ease-out duration-100"
        enterFrom="transform opacity-0 scale-95"
        enterTo="transform opacity-100 scale-100"
        leave="transition ease-in duration-75" 
        leaveFrom="transform opacity-100 scale-100" 
        leaveTo="transform opacity-0 scale-95"
    >
        <MenuItems class="absolute mt-2 right-0 max-w-[15rem] origin-top-right divide-y divide-gray-100 rounded-md bg-butCol dark:bg-white shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none">
            {#each filterListItems as item}
                <!-- svelte-ignore a11y-click-events-have-key-events -->
                <div class="px-1 py-1" on:click={ev => filterInvoices(item)}>
                    <MenuItem class="capitalize cursor-pointer text-xs font-medium py-1.5 px-5 text-white">
                        <span class="w-full">{item}</span>
                    </MenuItem>
                </div>
            {/each}
        </MenuItems>
    </Transition>
</Menu>