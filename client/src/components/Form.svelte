<script>
  import { setContext } from 'svelte';
  import { createEventDispatcher } from 'svelte';
  import { writable } from 'svelte/store';

  export let form = {};

  let formEl;

  const dispatch = createEventDispatcher();
  let errors = writable({});

  function onBlur(e) {
    validateField(e.target.name, e.target.value);
  }

  function isFormValid() {
    return !Object.values($errors).some((field) =>
      Object.values(field).some(
        errorObject => errorObject.error,
      ),
    );
  }

  function validateField(field, value) {
    form[field]?.validators &&
      form[field].validators.forEach((fn) => {
        const error = fn(value);
        errors.update((e) => {
          e[field] = { ...e[field], ...error };
          return e;
        });
      });
  }

  function validateForm(data) {
    Object.keys(data).forEach((field) => validateField(field, data[field]));
  }

  function onSubmit(e) {
    const formData = new FormData(e.target);

    const data = {};
    for (let field of formData) {
      const [key, value] = field;
      data[key] = value;
    }
    validateForm(data);

    return dispatch('submit', { valid: isFormValid(), data });
  }

  export function reset() {
    formEl.reset();
  }

  setContext('form', { errors, onBlur });
</script>

<form on:submit|preventDefault={onSubmit} bind:this={formEl}>
  <slot />
</form>

<style>
  form {
    display: flex;
    flex-direction: column;
    width: 300px;
  }

  :global(form > div) {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
  }

  :global(form > div + *) {
    margin-top: 10px;
  }
</style>