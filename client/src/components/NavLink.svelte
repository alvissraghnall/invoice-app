<script>
    import { invoiceModalOpen } from "../store";
    import { onMount } from "svelte";

    let dropdownElement;
    export let invoiceNumber = 6;

    const toggleFilterDropDown = () => {
        if (dropdownElement.classList.contains("hidden")) {
            dropdownElement.classList.remove("hidden");
            dropdownElement.classList.add("show");
        } else {
            dropdownElement.classList.remove("show");
            dropdownElement.classList.add("hidden");
        }
    }
    
    const showInvoiceModal = () => {
        invoiceModalOpen.update(() => !$invoiceModalOpen);
    }

    // onMount(() => {
    //     document.addEventListener("click", () => {
    //     if (dropdownElement.classList.contains("show")) {
    //         dropdownElement.classList.remove('show');
    //         dropdownElement.classList.add("hidden");
    //         console.log("now hidden...");
    //     }

    //     })
    // });


</script>


<div>

    <div class="desktop">
        <div class="holder">
            <div class="aka-ekpe">
                <h2>
                    invoices
                </h2>
                <span>
                    There are {invoiceNumber} total invoices
                </span>
            </div>
            <div class="aka-nri">
                <div class="filter">
                    <button on:click={toggleFilterDropDown}> 
                        <span>Filter by:</span>
                        <span class="dropdownicon">
                            <!-- <MdKeyboardArrowDown /> -->
                        </span>
                    </button>
                    <div class="hidden" bind:this={dropdownElement}>
                        <div class="in-group">
                            <input type="checkbox" name="draft" id="draft">
                            <label for="draft"> Draft </label>
                        </div>
                        <div class="in-group">
                            <input type="checkbox" name="pending" id="pending">
                            <label for="pending"> Pending </label>
                        </div>
                        <div class="in-group">
                            <input type="checkbox" name="paid" id="paid">
                            <label for="paid"> Paid </label>
                        </div>
                    </div>
                </div>

                <div class="new-invoice">
                    <button on:click={showInvoiceModal}>
                        <span class="w-8 h-8"> 
                            <!-- <MdAddCircleOutline /> -->
                        </span>
                        <span> new invoice </span>
                    </button>
                </div>
            </div>
        </div>
    </div>


</div>

<style>

    :global(.hidden) {
        display: none;
    }

    :global(.show) {
        display: block;
    }

    .desktop {
        display: none;
        margin-left: auto;
        margin-right: auto;
        max-width: 100%;
        padding-top: 3.4rem;
        padding-left: .6rem;
        padding-right: .6rem;
        line-height: 1.7;
    }


    .holder {
        display: flex;
        align-items: center;
        flex-wrap: wrap;
        margin-left: -.88rem;
        margin-right: -.88rem;
    }

    .holder > * {
        padding-right: -.88rem;
        padding-left: -.88rem;
        /* width: 100%; */
    }

    .holder > :first-child > h2 {
        font-size: 2.1rem;
        font-weight: 600;
        color: white;
        text-transform: capitalize;
    }

    .holder > :first-child > span {
        color: rgb(130, 158, 197);
        font-size: 13.5px;
    }

    .aka-nri {
        padding-left: 0;
        text-align: right;
    }

    .aka-nri > :first-child {
        position: relative;
        display: inline-block;
    }

    .aka-nri > :first-child:active > :nth-child(2) {
        display: block;

    }

    .dropdownicon {
        width: 32px;
        height: 32px;
    }

    .aka-nri > :first-child > :first-child {
        background-color: transparent;
        color: bisque;
        padding: 14px;
        font-size: 16px;
        border: none;
        cursor: pointer;
        display: flex;
        align-items: center;
        text-align: left;
    }

    .aka-nri > :first-child > :nth-child(2) {
        position: absolute;
        background-color: transparent;
        /* min-width: 160px; */
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        z-index: 1;
    }

    .aka-nri > :first-child > :nth-child(2) > div {
        color: black;
        padding: 12px 16px;
        list-style: none;
        display: block;
        opacity: 0.88;
    }

    .aka-nri > :first-child > :nth-child(2) > div:hover {
        opacity: 1;
    }

    .new-invoice > button {
        color: rgb(232, 230, 227);
        text-decoration-color: initial;
        background-color: green;
        border-color: transparent;
        padding: .5rem;
        display: inline;
        font-weight: 400;
        line-height: 1.5;
        border-radius: 25px;
    }

    .new-invoice > button > :nth-child(1) {
        width: 55px;
        height: 55px;
    }

    .new-invoice > button > :nth-child(2) {
        text-transform: capitalize;

    }
    

    @media screen and (min-width: 760px) {
        .desktop {
            max-width: 100%;
        }

        .holder {
            padding-top: 3.4rem;
        }

        .holder > :first-child {
            margin-left: 16.66666667%;
            flex: 0 0 auto;
            width: 25%;
        }

        .aka-nri {
            flex: 0 0 auto;
            width: 41%;
        }
    }

    @media screen and (min-width: 940px) {
        .desktop {
            display: block;
            max-width: 91%;
        }

        .holder {
            padding-top: 0;
        }
    }
</style>