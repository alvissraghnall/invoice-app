
<InvoiceMain />



<script>
    import InvoiceMain from "../components/InvoiceMain.svelte";
    let invoiceNumber = 6;

    const toggleFilterMenu = ev => {

    }
</script>


<style>
    .home {
        color: white;
    }

    .home > :first-child {
        margin-bottom: 58.8px;
    }

    .ekpe, .nri {
        flex: 1;
    }

    .nri {
        justify-content: flex-end;
        display: flex;
        align-items: center;
    }

    button {
        align-items: center;
    }

    button span {
        font-size: 14px;
    }
    
    h1 {
        text-transform: capitalize;
    }

    .nri > :first-child {
        font-size: 14px;
        font-weight: 600;
        background-color: transparent !important;
        padding-left: 1rem;
        position: relative;
    }
    
</style>