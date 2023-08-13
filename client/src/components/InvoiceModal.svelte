<script>
    import { Button, Input, Select } from "./shared";
    import { Icon, Trash, Plus } from "svelte-hero-icons";
    const submitForm = (ev) => {
        console.log(fields);
    };

    const deleteInvoice = (id) => {};

    const closeInvoice = (ev) => {};

    const createInvoice = (ev) => {};

    const saveDraft = (ev) => {};

    const addNewInvoiceItem = (ev) => {};
    $: itemsList = [];

    const fields = {
        billerStreetAddress: "",
        billerCity: "",
        billerZipCode: "",
        billerCountry: "",
        clientName: "",
        clientStreetAddress: "",
        clientEmail: "",
        clientCity: "",
        clientZipCode: "",
        clientCountry: "",
        invoiceDate: null,
        paymentTerms: null,
        paymentDueDate: null,
        productDesc: "",
        pending: null,
        invoiceDraft: null,
        invoiceItemList: [],
        invoiceTotal: 0,
    };
</script>

<div
    class="invoice-wrap fixed top-0 left-0 bg-transparent h-screen overflow-scroll [&::-webkit-scrollbar]:hidden [-ms-overflow-style:'none'] [scrollbar-width:'none'] lg:left-[5.62rem] flex flex-col"
>
    <form
        class="relative p-14 max-w-[44rem] w-full bg-holderColor text-white shadow-xl"
        on:submit|preventDefault={submitForm}
    >
        <h1 class="font-semibold text-lg mb-12 text-white">New Invoice</h1>

        <div class="bill-from mb-12 flex flex-col">
            <h4 class="text-purple-800">Bill From</h4>

            <div class="input flex flex-col mb-6">
                <label class="text-sm mb-1.5" for="billerStreetAddress"
                    >Street Address</label
                >
                <Input
                    class="mb-6"
                    type="text"
                    id="billerStreetAddress"
                    bind:value={fields.billerStreetAddress}
                />
            </div>

            <div class="location-details gap-4 flex">
                <div class="input flex flex-col mb-6 flex-1">
                    <label class="text-sm mb-1.5" for="billerCity">City</label>
                    <Input
                        class="mb-6"
                        type="text"
                        id="billerCity"
                        bind:value={fields.billerCity}
                    />
                </div>
                <div class="input flex flex-col mb-6 flex-1">
                    <label class="text-sm mb-1.5" for="billerbillerZipCode"
                        >Zip Code</label
                    >
                    <Input
                        class="mb-6"
                        type="number"
                        id="billerbillerZipCode"
                        bind:value={fields.billerZipCode}
                    />
                </div>
                <div class="input flex flex-col mb-6 flex-1">
                    <label class="text-sm mb-1.5" for="billerCountry"
                        >Country</label
                    >
                    <Input
                        class="mb-6"
                        type="text"
                        id="billerCountry"
                        bind:value={fields.billerCountry}
                    />
                </div>
            </div>
        </div>

        <!-- Bill To -->
        <div class="bill-to mb-12 flex-col flex">
            <h4 class="text-[#7152f0] text-xs mb-6">Bill To</h4>

            <div class="input flex flex-col mb-6">
                <label class="text-sm mb-1.5" for="clientName"
                    >Client's Name</label
                >
                <Input
                    class="mb-6"
                    type="text"
                    id="clientName"
                    bind:value={fields.clientName}
                />
            </div>
            <div class="input flex flex-col mb-6">
                <label class="text-sm mb-1.5" for="clientEmail"
                    >Client's Email</label
                >
                <Input
                    class="mb-6"
                    type="text"
                    id="clientEmail"
                    bind:value={fields.clientEmail}
                />
            </div>
            <div class="input flex flex-col mb-6">
                <label class="text-sm mb-1.5" for="clientAddress"
                    >Client's Address</label
                >
                <Input
                    class="mb-6"
                    type="text"
                    id="clientAddress"
                    bind:value={fields.clientStreetAddress}
                />
            </div>

            <div class="input flex flex-col mb-6">
                <label class="text-sm mb-1.5" for="clientCity"
                    >Client's City</label
                >
                <Input
                    class="mb-6"
                    type="text"
                    id="clientCity"
                    bind:value={fields.clientCity}
                />
            </div>
            <div class="input flex flex-col mb-6">
                <label class="text-sm mb-1.5" for="clientZip"
                    >Client's Zip Code</label
                >
                <Input
                    class="mb-6"
                    type="text"
                    id="clientZip"
                    bind:value={fields.clientZipCode}
                />
            </div>
            <div class="input flex flex-col mb-6">
                <label class="text-sm mb-1.5" for="clientCountry"
                    >Client's Country</label
                >
                <Input
                    class="mb-6"
                    type="text"
                    id="clientCountry"
                    bind:value={fields.clientCountry}
                />
            </div>
        </div>

        <!-- Invoice Details -->
        <div class="invoice-details flex flex-col">
            <div class="payment flex gap-6">
                <div class="input flex flex-col mb-6 flex-1">
                    <label class="text-sm mb-1.5" for="invoiceDate"
                        >Invoice Date</label
                    >
                    <Input 
                        class="mb-6" 
                        type="text" 
                        id="invoiceDate" 
                        disabled 
                        value={fields.invoiceDate}
                    />
                </div>
                <div class="input flex flex-col mb-6 flex-1">
                    <label class="text-sm mb-1.5" for="paymentDueDate"
                        >Payment Due Date</label
                    >
                    <Input
                        class="mb-6"
                        type="text"
                        id="paymentDueDate"
                        disabled
                        value={fields.paymentDueDate}
                    />
                </div>
            </div>

            <div class="input flex flex-col mb-6 flex-1">
                <label for="paymentTerms">Payment Terms</label>
                <Select id="paymentTerms" bind:value={fields.paymentTerms}>
                    <option value={30}>Net 30 Days</option>
                    <option value={60}>Net 60 Days</option>
                    <option value={90}>Net 90 Days</option>
                </Select>
            </div>

            <div class="input flex flex-col mb-6 flex-1">
                <label for="productDesc"
                    >Payment Description</label
                >
                <Input
                    class="mb-6"
                    type="text"
                    id="productDesc"
                    bind:value={fields.productDesc}
                />
            </div>

            <div class="items-list">
                <h3 class="text-[#777f98] text-base mb-4">Item List</h3>
                <table class="it-list w-full">
                    <tr class="t_heading mb-4 gap-4 text-xs">
                        <th class="text-left basis-2/4">Item Name</th>
                        <th class="text-left basis-1/12">Qty.</th>
                        <th class="text-left basis-1/5">Price</th>
                        <th class="text-left basis-1/5 self-center">Total</th>
                    </tr>
                    {#each itemsList as item, index (item.id)}
                    <tr class="tb-items relative mb-6 gap-4 text-xs flex">
                        <td class="basis-2/4">
                            <Input
                                class="mb-6"
                                type="text"
                                value={item.itemName}
                            />
                        </td>
                        <td class="basis-1/12">
                            <Input 
                                class="mb-6" 
                                type="text" 
                                value={item.qty} 
                            />
                        </td>
                        <td class="basis-1/5">
                            <Input
                                class="mb-6"
                                type="text"
                                value={item.price}
                            />
                        </td>
                        <td class="flex basis-1/5 self-center">
                            $ {(item.total = item.qty * item.price)}
                        </td>
                        <!-- svelte-ignore a11y-click-events-have-key-events -->
                        <td class="absolute top-4 h-4 w-3 right-0" on:click={() => deleteInvoice(item.id)}>
                            <Icon src={Trash} />
                        </td>
                    </tr>
                    {/each}
                </table>

                <Button
                    type="submit"
                    class="flex button text-white/85 bg-butCol items-center justify-center w-full !py-3 mt-5"
                    on:click={addNewInvoiceItem}
                >
                    <span class="w-5 h-5 mr-2.5">
                        <Icon src={Plus} />
                    </span>
                    Add New Item
                </Button>
            </div>
        </div>

        <div class="save flex mt-12">
            <div class="flex-1">
                <Button
                    type="button"
                    class="capitalize bg-red-600"
                    on:click={closeInvoice}
                >
                    cancel
                </Button>
            </div>
            <div class="flex justify-end flex-1 space-x-2">
                <Button
                    type="button"
                    class="bg-butCol capitalize"
                    on:click={saveDraft}
                >
                    save draft
                </Button>
                <Button
                    type="button"
                    class="bg-purple-900 capitalize"
                    on:click={createInvoice}
                >
                    Create Invoice
                </Button>
            </div>
        </div>
    </form>
</div>
