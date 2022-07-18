<script>
  import { getContext } from 'svelte';
  export let type = 'text';
  export let label;
  export let name;
  const { onBlur } = getContext('form');
</script>

<label for={name}>{label}</label>
<input {name} {type} on:blur={onBlur} />
