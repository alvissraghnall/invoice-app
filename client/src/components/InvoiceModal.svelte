<script>
import {Icon, Trash, Plus} from "svelte-hero-icons";
const submitForm = ev => {

}

const deleteInvoice = id => {

}

const closeInvoice = ev => {

}

const createInvoice = ev => {

}

const addNewInvoiceItem = ev => {

}
$: itemsList = [];

</script>

<div class="invoice-wrap fixed top-0 left-0 bg-transparent w-full h-screen overflow-scroll lg:left-[5.62rem] flex flex-col">
    <form class="relative p-14 max-w-[44rem] w-full bg-holderColor text-white shadow-xl" on:submit|preventDefault={submitForm}>
        <h1 class="font-semibold text-lg mb-12 text-white">New Invoice</h1>

        <div class="bill-from flex flex-col">
            <h4 class="text-purple-800 ">Bill From</h4>
            
            <div class="input flex flex-col mb-6">
                <label class="text-sm mb-1.5" for="biller-address">Street Address</label>
                <input type="text" id="biller-address">
            </div>

            <div class="location-details flex">
                <div class="input flex flex-col mb-6">
                    <label class="text-sm mb-1.5" for="biller-city">City</label>
                    <input type="text" id="biller-city">
                </div>
                <div class="input flex flex-col mb-6">
                    <label class="text-sm mb-1.5" for="biller-zip-code">Zip Code</label>
                    <input type="number" id="biller-zip-code">
                </div>
                <div class="input flex flex-col mb-6">
                    <label class="text-sm mb-1.5" for="biller-country">Country</label>
                    <input type="text" id="biller-country">
                </div>
            </div>
        </div>

        <!-- Bill To -->
        <div class="bill-to flex-col flex">
            <h4 class="text-[#7152f0] text-xs mb-6">Bill To</h4>

            <div class="input flex flex-col mb-6">
                <label class="text-sm mb-1.5" for="client-name">Client's Name</label>
                <input type="text" id="client-name">
            </div>
            <div class="input flex flex-col mb-6">
                <label class="text-sm mb-1.5" for="client-email">Client's Email</label>
                <input type="text" id="client-email">
            </div>
            <div class="input flex flex-col mb-6">
                <label class="text-sm mb-1.5" for="client-address">Client's Address</label>
                <input type="text" id="client-address">
            </div>

            <div class="input flex flex-col mb-6">
                <label class="text-sm mb-1.5" for="client-city">Client's City</label>
                <input type="text" id="client-city">
            </div>
            <div class="input flex flex-col mb-6">
                <label class="text-sm mb-1.5" for="client-zip">Client's Zip Code</label>
                <input type="text" id="client-zip">
            </div>
            <div class="input flex flex-col mb-6">
                <label class="text-sm mb-1.5" for="client-country">Client's Country</label>
                <input type="text" id="client-country">
            </div>
        </div>

        <!-- Invoice Details -->
        <div class="invoice-details flex flex-col">
            <div class="payment flex">
                <div class="input flex flex-col mb-6">
                    <label class="text-sm mb-1.5" for="invoiceDate">Invoice Date</label>
                    <input type="text" id="invoiceDate" disabled>
                </div>
                <div class="input flex flex-col mb-6">
                    <label class="text-sm mb-1.5" for="paymentDueDate">Payment Due Date</label>
                    <input type="text" id="paymentDueDate" disabled>
                </div>
            </div>

            <div class="input flex flex-col mb-6">
                <label for="paymentTerms">Payment Terms</label>
                <select id="paymentTerms">
                    <option value="30">Net 30 Days</option>
                    <option value="60">Net 60 Days</option>
                    <option value="90">Net 90 Days</option>
                </select>
            </div> class="text-sm mb-1.5"

            <div class="input flex flex-col mb-6">
                <label for="paymentDescription">Payment Description</label>
                <input type="text" id="paymentDescription">
            </div> class="text-sm mb-1.5"

            <div class="items-list">
                <h3 class="text-[#777f98] text-base mb-4">Item List</h3>
                <table class="it-list">
                    <tr class="t_heading">
                        <th>Item Name</th>
                        <th>Qty.</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                    {#each itemsList as item, index (item.id)}
                        <td>
                            <input type="text" value={item.itemName}>
                        </td>
                        <td>
                            <input type="text" value={item.qty}>
                        </td>
                        <td>
                            <input type="text" value={item.price}>
                        </td>
                        <td class="flex">
                            $ { item.total = item.qty * item.price }
                        </td>
                        <!-- svelte-ignore a11y-click-events-have-key-events -->
                        <td on:click={() => deleteInvoice(item.id)}>
                            <Icon src={Trash} />
                        </td>
                    {/each}
                </table>

                <button type="button" class="flex button" on:click={addNewInvoiceItem}>
                    <span>
                        <Icon src={Plus} />
                    </span>
                    Add New Invoice
                </button>
            </div>
        </div>

        <div class="save flex">
            <div class="">
                <button type="button" class="capitalize bg-red-600" on:click={closeInvoice}>
                    cancel
                </button>
            </div>
            <div class="flex">
                <button type="button" class="bg-purple-900 capitalize" on:click={createInvoice}>
                    save draft
                </button>
            </div>
        </div>
    </form>
</div>

<style>
    input, select {
        @apply w-full bg-regColor text-white rounded py-3 px-1 border-none;
    }
</style>