<script>
  import { getContext } from 'svelte';
  export let type = 'text';
  export let label;
  export let name;
  const { onBlur } = getContext('form');
</script>

{#if label}
  <label for={name}>{label}</label>
{/if}
<input {name} {type} on:blur={onBlur} />
