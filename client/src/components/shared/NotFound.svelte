<div class="flex flex-col mt-20 items-center">
    <img class="w-52 h-48" src="/assets/illustration-empty.svg" alt="" />
    <h3 class="text-2xl mt-10 text-black dark:text-white">There is nothing here!</h3>
    <p class="text-center text-sm max-w-xs font-light mt-4 text-neutral-700 dark:text-white/90">
        {param ? 'No invoice with ID provided exists!' : 'Create a new invoice by clicking the "Add New Invoice" button and get started.'}
    </p>
</div>

<script>
    import { onMount } from "svelte";

    export let currentRoute;

    let param;

    onMount(() => {
        param = currentRoute?.namedParams?.invoiceId;
    });
</script>