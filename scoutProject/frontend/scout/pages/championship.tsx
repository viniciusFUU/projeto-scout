import { useEffect, useState } from "react";
import { StyleSheet, Text, View } from "react-native";
import apiCliente from '../api/apiClient';

// Definindo a interface para os dados do campeonato
interface ChampionshipType {
    championshipId: number;
    championshipName: string;
}

function Championship() {
    // Definindo o tipo para o estado Championship
    const [Championship, setChampionship] = useState<ChampionshipType[]>([]);

    useEffect(() => {
        apiCliente.get('/championship')
            .then(response => {
                setChampionship(response.data);
            })
            .catch(error => {
                console.error('Erro ao buscar dados: ', error);
            });
    }, []);

    return (
        <View style={styles.container}>
            {Championship.map(champ => (
                <Text key={champ.championshipId}>{champ.championshipName}</Text>
            ))}
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        backgroundColor: "#a4a4a4"
    }
});

export default Championship;
