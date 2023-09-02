<script>
    export let status;
</script>

<div class={
    'flex text-xs items-center py-4 px-9 max-md:px-7 rounded-xl before:w-2.5 before:h-2.5 before:rounded-full before:mr-[0.37rem] '
    .concat(
        (status === 'COMPLETED' && 'bg-green-100 dark:bg-green-800/40 text-green-500 before:bg-green-500')
    || (status === 'DRAFT' && 'bg-neutral-100 dark:bg-[rgba(223,227,250,0.1)] text-[rgb(55,59,83)] dark:text-[rgb(223,227,250)] before:bg-[rgb(223,227,250)]')
    || (status === 'PENDING' && 'bg-yellow-100 dark:bg-yellow-700/40 text-yellow-400 before:bg-yellow-400')
    || (status === 'CANCELLED' && 'bg-red-100 dark:bg-red-800/30 text-red-500 before:bg-red-500')
    )
}>
    <span>{status}</span>
</div>