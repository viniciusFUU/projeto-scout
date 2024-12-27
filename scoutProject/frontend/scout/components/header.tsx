import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

function Header() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Scout</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#000015',
    padding: 16,
    alignItems: 'center',
  },
  title: {
    color: '#fff',
    fontSize: 20,
    fontWeight: 'bold', 
  },
});

export default Header;